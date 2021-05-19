package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.model.entity.ChatMessage;
import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.service.impl.ChatMessageServiceImpl;
import com.hivein.userdataservice.util.StatusCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/msg")
@RequiredArgsConstructor
public class MessageController {

    private final ChatMessageServiceImpl chatMessageService;

    @PostMapping("/save")
    public ResponseEntity<?> saveMessage(@RequestBody ChatMessage chatMessage) {
        log.info("Inside saveMessage in chat controller");
        return ResponseEntity.ok(chatMessageService.save(chatMessage));
    }

    @GetMapping("/count/new/{senderId}/{recipientId}")
    public ResponseEntity<?> countNewMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        log.info("Counting new messages");
        long count = chatMessageService.countNewMessages(senderId, recipientId);
        log.info("New Messages found: " + count);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/get/{senderId}/{recipientId}")
    public ResponseEntity<?> getMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        log.info("Inside get messages");
        List<ChatMessage> messages = chatMessageService.findChatMessages(senderId, recipientId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMessage(@PathVariable long id) {
        log.info("Inside get message by id");
        ChatMessage chatMessage = chatMessageService.findById(id);
        return ResponseEntity.ok(chatMessage);
    }
}
