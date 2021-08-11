package com.bluayer.simsimfree.repository

import com.bluayer.simsimfree.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository: JpaRepository<ChatRoom, Long?>{
}