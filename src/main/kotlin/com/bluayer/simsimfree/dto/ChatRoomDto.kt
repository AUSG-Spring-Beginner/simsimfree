package com.bluayer.simsimfree.dto

import com.bluayer.simsimfree.entity.ChatMessage
import com.bluayer.simsimfree.entity.MessageType
import com.bluayer.simsimfree.service.ChatService
import org.springframework.web.socket.WebSocketSession

class ChatRoomDto(
    private var id: Long?,
    private var name: String
) {
    private var sessions: Set<WebSocketSession> = HashSet()

    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type.equals(MessageType.JOIN)) {
            sessions.plus(session)
            chatMessage.content = chatMessage.sender + "님이 입장했습니다."
        }
        sendMessage(chatMessage, chatService)
    }

    fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach { session -> chatService.sendMessage(session, message) }
    }
}
