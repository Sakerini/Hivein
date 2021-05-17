package com.hivein.chatservice.repositoory;

import com.hivein.chatservice.model.MessageStatus;
import com.hivein.chatservice.model.enitity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, String status);

    List<ChatMessage> findByChatId(String chatId);

    @Transactional
    @Modifying
    @Query(value = "update chat_messages set status = :status " +
            "where sender_id = :senderId and recipient_id = :recipientId",
    nativeQuery = true)
    void updateStatuses(@Param("senderId") String senderId, @Param("recipientId") String recipientId, @Param("status") String status);
}
