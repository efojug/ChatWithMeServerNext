package com.efojug.chatwithmeservernext.entity

import jakarta.persistence.*

@Entity
@Table(name = "friend")
open class Friend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    open var userId: Long = 0,

    @Column(name = "friend_id", nullable = false)
    open var friendId: Long = 0
)