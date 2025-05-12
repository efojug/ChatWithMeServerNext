package com.efojug.chatwithmeservernext.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "message")
open class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    open var fromUser: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    open var toUser: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    open var room: ChatRoom? = null,

    @Column(nullable = false, columnDefinition = "TEXT")
    open var content: String = "",

    @Column(nullable = false)
    open var timestamp: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    open var isRead: Boolean = false
)