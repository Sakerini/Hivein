package com.hivein.chatservice.api;

import com.hivein.chatservice.model.ChatMessage;
import com.hivein.chatservice.model.ChatRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "${userdata.service.name}", url = "${userdata.service.base.url}")
public interface ChatStorageApi {

    @GetMapping("/room/get/{senderId}/{recipientId}")
    ChatRoom getRoom(@PathVariable String senderId, @PathVariable String recipientId);

    @PostMapping("/room/create/{senderId}/{recipientId}")
    void createRoom(@PathVariable String senderId, @PathVariable String recipientId);

    @PostMapping("/msg/save")
    ChatMessage saveMessage(@RequestBody ChatMessage chatMessage);

    @GetMapping("/msg/count/new/{senderId}/{recipientId}")
    long countNewMessages(@PathVariable String senderId, @PathVariable String recipientId);

    @GetMapping("/msg/get/{senderId}/{recipientId}")
    List<ChatMessage> getMessages(@PathVariable String senderId, @PathVariable String recipientId);

    @GetMapping("/msg/get/{id}")
    ChatMessage getMessage(@PathVariable long id);
}
