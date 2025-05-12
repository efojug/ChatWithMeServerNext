package com.efojug.chatwithmeservernext.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(unique = true, nullable = false)
    open var username: String = "",

    @Column(nullable = false)
    open var passwordHash: String = "",

    @Column(nullable = false)
    open var nickname: String = "",

    open var avatarUrl: String? = null,
    open var backgroundUrl: String? = null
)