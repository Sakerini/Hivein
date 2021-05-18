package com.hivein.userdataservice.service.impl;

import com.hivein.userdataservice.exception.ResourceNotFoundException;
import com.hivein.userdataservice.model.entity.ChatMessage;
import com.hivein.userdataservice.model.entity.ChatRoom;
import com.hivein.userdataservice.repository.ChatMessageRepository;
import com.hivein.userdataservice.util.MessageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageServiceImpl {

    private final ChatMessageRepository repository;
    private final ChatRoomServiceImpl chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        log.info("Recieved a message: Saving");
        chatMessage.setStatus(MessageStatus.RECEIVED.toString());
        String chatId = String.format("%s_%s", chatMessage.getSenderId(), chatMessage.getRecipientId());
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        log.info("Message saved");
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return repository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, MessageStatus.RECEIVED.toString());
    }

    public List<ChatMessage>    findChatMessages(String senderId, String recipientId) {
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoom(senderId, recipientId);

        List<ChatMessage> messages =
                chatRoom.map(cId -> repository.findByChatId(cId.getChatId())).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public ChatMessage findById(long id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED.toString());
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        repository.updateStatuses(senderId, recipientId, status.toString());
    }
}
