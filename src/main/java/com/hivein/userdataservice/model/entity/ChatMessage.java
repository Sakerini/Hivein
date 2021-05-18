package com.hivein.userdataservice.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   private String chatId;
   private String senderId;
   private String recipientId;
   private String senderName;
   private String recipientName;
   private String content;
   private Date timestamp;
   private String status;
}
