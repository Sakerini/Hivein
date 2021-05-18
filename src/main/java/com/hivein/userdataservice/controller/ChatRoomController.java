package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.model.entity.ChatRoom;
import com.hivein.userdataservice.model.response.RoomCreationResponse;
import com.hivein.userdataservice.service.impl.ChatRoomServiceImpl;
import com.hivein.userdataservice.util.StatusCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomServiceImpl chatRoomService;

    @GetMapping("/get/{senderId}/{recipientId}")
    public ResponseEntity<?> getRoom(@PathVariable String senderId, @PathVariable String recipientId) {
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoom(senderId, recipientId);
        if (chatRoom.isPresent()) {
            return ResponseEntity.ok(chatRoom.get());
        } else
            return ResponseEntity.ok(Optional.empty());
    }

    @PostMapping("/create/{senderId}/{recipientId}")
    public ResponseEntity<?> createRoom(@PathVariable String senderId, @PathVariable String recipientId) {
        try {
            chatRoomService.createChatRooms(senderId, recipientId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RoomCreationResponse(StatusCodes.BAD_REQUEST.getCode(), "Room Already exists"));
        }
        return ResponseEntity.ok(new RoomCreationResponse(StatusCodes.OK.getCode(), "Room created"));
    }
}
