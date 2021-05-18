package com.hivein.chatservice.service;

import com.hivein.chatservice.model.ChatMessage;
import com.hivein.chatservice.model.ChatRoom;

import java.util.List;

public interface ChatStorageService {

    ChatRoom getRoom(String senderId, String recipientId);

    void createRoom(String senderId, String recipientId);

    ChatMessage saveMessage(ChatMessage chatMessage);

    long countNewMessages(String senderId, String recipientId);

    List<ChatMessage> getMessages(String senderId, String recipientId);

    ChatMessage getMessage(long id);
}
