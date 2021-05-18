package com.hivein.chatservice.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
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
