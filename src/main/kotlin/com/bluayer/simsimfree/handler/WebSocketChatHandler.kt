package com.bluayer.simsimfree.handler

import com.bluayer.simsimfree.entity.ChatMessage
import com.bluayer.simsimfree.entity.ChatRoom
import com.bluayer.simsimfree.service.ChatService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketChatHandler(val chatService: ChatService) : TextWebSocketHandler() {
    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload: String = message.payload
        val chatMessage = objectMapper.readValue(payload, ChatMessage::class.java)
        val room: ChatRoom = chatService.findById(chatMessage.roomId)
        room.handleActions(session, chatMessage, chatService)
    }
}