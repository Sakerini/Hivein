package com.hivein.userdataservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "chat_rooms")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
