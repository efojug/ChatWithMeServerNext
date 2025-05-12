package com.efojug.chatwithmeservernext.repository

import com.efojug.chatwithmeservernext.entity.FriendRequest
import com.efojug.chatwithmeservernext.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface FriendRequestRepository : JpaRepository<FriendRequest, Long> {
    fun findByToUserAndStatus(toUser: User, status: String): List<FriendRequest>
}