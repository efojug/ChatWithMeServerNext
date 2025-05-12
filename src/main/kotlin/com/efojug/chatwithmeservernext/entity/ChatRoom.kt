package com.efojug.chatwithmeservernext.entity

import jakarta.persistence.*

@Entity
@Table(name = "chat_room")
open class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(nullable = false)
    open var name: String = "",

    @Column(nullable = false)
    open var isGroup: Boolean = true
)