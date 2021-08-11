package com.bluayer.simsimfree.controller

import com.bluayer.simsimfree.entity.ChatMessage
import com.bluayer.simsimfree.entity.ChatRoom
import com.bluayer.simsimfree.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController (private val chatService: ChatService){
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage?): ChatMessage? {
        return chatMessage
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage, headerAccessor: SimpMessageHeaderAccessor): ChatMessage? {
        headerAccessor.sessionAttributes!!["username"] = chatMessage.sender
        return chatMessage
    }

    @PostMapping("/room")
    fun createChatRoom(@RequestParam name: String): ChatRoom {
        return chatService.createRoom(name)
    }

    @GetMapping("/room")
    fun findAllRoom(): List<ChatRoom> {
        return chatService.findAllRoom()
    }
}