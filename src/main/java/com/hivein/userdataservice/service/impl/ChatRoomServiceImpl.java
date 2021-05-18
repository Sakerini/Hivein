package com.hivein.userdataservice.service.impl;

import com.hivein.userdataservice.exception.RoomAlreadyExistsException;
import com.hivein.userdataservice.model.entity.ChatRoom;
import com.hivein.userdataservice.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatRoomServiceImpl {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<ChatRoom> getChatRoom(String senderId, String recipientId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId);
        return chatRoom;
    }

    public void createChatRooms(String senderId, String recipientId) throws RoomAlreadyExistsException {
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        String chatIdReverse = String.format("%s_%s", recipientId, senderId);

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatIdReverse)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
    }
}
