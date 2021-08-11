package com.bluayer.simsimfree.entity

import com.bluayer.simsimfree.service.ChatService
import org.springframework.web.socket.WebSocketSession
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "CHATROOM_TB")
class ChatRoom (name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    @Transient
    var sessions: Set<WebSocketSession> = HashSet()

    fun handleActions(session: WebSocketSession, chatMessage: ChatMessage, chatService: ChatService) {
        if (chatMessage.type.equals(MessageType.JOIN)) {
            sessions.plus(session)
            chatMessage.content = chatMessage.sender + "님이 입장했습니다."
        }
        sendMessage(chatMessage, chatService)
    }

    private fun <T> sendMessage(message: T, chatService: ChatService) {
        sessions.parallelStream().forEach { session -> chatService.sendMessage(session, message) }
    }
}