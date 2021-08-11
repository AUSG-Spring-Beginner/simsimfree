package com.bluayer.simsimfree.entity

import javax.persistence.*

@Entity
@Table(name = "GROUP_TB")
class Group (name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name
}