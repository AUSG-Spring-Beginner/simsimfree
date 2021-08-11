package com.bluayer.simsimfree.entity

data class ChatMessage (
    var type: MessageType,
    var content: String?,
    var sender: String,
    var roomId: Long
)