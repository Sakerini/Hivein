package com.hivein.chatservice.controller;

import com.hivein.chatservice.model.ChatNotification;
import com.hivein.chatservice.model.ChatMessage;
import com.hivein.chatservice.model.ChatRoom;
import com.hivein.chatservice.service.ChatStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatStorageService chatStorageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        ChatRoom chatRoom = chatStorageService.getRoom(chatMessage.getSenderId(), chatMessage.getRecipientId());
        if (Objects.isNull(chatRoom)) {
            chatStorageService.createRoom(chatMessage.getSenderId(), chatMessage.getRecipientId());
            chatRoom = chatStorageService.getRoom(chatMessage.getSenderId(), chatMessage.getRecipientId());
        }
        chatMessage.setChatId(chatRoom.getChatId());

        ChatMessage saved = chatStorageService.saveMessage(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String recipientId) {

        log.info("Counting new messages");
        return ResponseEntity
                .ok(chatStorageService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String recipientId) {
        return ResponseEntity
                .ok(chatStorageService.getMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable long id) {
        return ResponseEntity
                .ok(chatStorageService.getMessage(id));
    }
}
