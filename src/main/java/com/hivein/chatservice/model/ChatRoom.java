package com.hivein.chatservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    private long id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
