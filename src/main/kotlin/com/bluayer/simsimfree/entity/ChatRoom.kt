package com.bluayer.simsimfree.entity

import javax.persistence.*

@Entity
@Table(name = "CHATROOM_TB")
class ChatRoom (name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name
}