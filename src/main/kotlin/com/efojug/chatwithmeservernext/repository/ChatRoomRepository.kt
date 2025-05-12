package com.efojug.chatwithmeservernext.repository

import com.efojug.chatwithmeservernext.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom, Long>