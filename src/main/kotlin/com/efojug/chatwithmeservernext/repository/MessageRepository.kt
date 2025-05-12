package com.efojug.chatwithmeservernext.repository

import com.efojug.chatwithmeservernext.entity.Message
import com.efojug.chatwithmeservernext.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface MessageRepository : JpaRepository<Message, Long> {
    fun findByToUserAndIsRead(toUser: User, isRead: Boolean): List<Message>
    fun findByRoomIdOrderByTimestamp(roomId: Long): List<Message>
    fun findByToUserIdOrderByTimestamp(toUserId: Long): List<Message>
    fun findByTimestampAfter(timestamp: LocalDateTime): List<Message>
}