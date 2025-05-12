package com.efojug.chatwithmeservernext.entity

import jakarta.persistence.*

@Entity
@Table(name = "friend_request")
open class FriendRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    open var fromUser: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    open var toUser: User? = null,

    @Column(nullable = false)
    open var status: String = "PENDING"
)