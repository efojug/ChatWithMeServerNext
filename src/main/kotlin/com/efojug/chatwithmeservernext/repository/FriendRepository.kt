package com.efojug.chatwithmeservernext.repository

import com.efojug.chatwithmeservernext.entity.Friend
import org.springframework.data.jpa.repository.JpaRepository

interface FriendRepository : JpaRepository<Friend, Long> {
    fun findByUserId(userId: Long): List<Friend>
}