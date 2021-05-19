package com.hivein.chatservice.service.impl;

import com.hivein.chatservice.api.ChatStorageApi;
import com.hivein.chatservice.model.ChatMessage;
import com.hivein.chatservice.model.ChatRoom;
import com.hivein.chatservice.service.ChatStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatStorageServiceImpl implements ChatStorageService {

    private final ChatStorageApi chatStorageApi;

    @Override
    public ChatRoom getRoom(String senderId, String recipientId) {
        log.info("inside ChatStorageSerice getRoom");
        return chatStorageApi.getRoom(senderId,recipientId);
    }

    @Override
    public void createRoom(String senderId, String recipientId) {
        chatStorageApi.createRoom(senderId, recipientId);
    }

    @Override
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        System.out.println();
        return chatStorageApi.saveMessage(chatMessage);
    }

    @Override
    public long countNewMessages(String senderId, String recipientId) {
        return chatStorageApi.countNewMessages(senderId, recipientId);
    }

    @Override
    public List<ChatMessage> getMessages(String senderId, String recipientId) {
        return chatStorageApi.getMessages(senderId, recipientId);
    }

    @Override
    public ChatMessage getMessage(long id) {
        return chatStorageApi.getMessage(id);
    }
}
