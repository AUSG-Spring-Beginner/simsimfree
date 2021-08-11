package com.bluayer.simsimfree.service

import com.bluayer.simsimfree.entity.ChatRoom
import com.bluayer.simsimfree.repository.ChatRoomRepository
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.io.IOException
import java.lang.RuntimeException

@Service
@Transactional
class ChatService(val chatRoomRepository: ChatRoomRepository) {
    private val objectMapper = ObjectMapper()
    private val logger = KotlinLogging.logger {}

    fun createRoom (name: String): ChatRoom {
        val chatRoom = ChatRoom(name)
        return chatRoomRepository.save(chatRoom)
    }

    fun findAllRoom(): List<ChatRoom> {
        return chatRoomRepository.findAll()
    }

    fun findById(id: Long): ChatRoom {
        return chatRoomRepository.findById(id).get()
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
        } catch (e: IOException) {
            logger.error { e.message }
        }
    }
}