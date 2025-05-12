package com.efojug.chatwithmeservernext.service

import com.efojug.chatwithmeservernext.dto.ChatMessageDto
import com.efojug.chatwithmeservernext.entity.Message
import com.efojug.chatwithmeservernext.repository.ChatRoomRepository
import com.efojug.chatwithmeservernext.repository.MessageRepository
import com.efojug.chatwithmeservernext.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class ChatService(
    private val messageRepo: MessageRepository,
    private val userRepo: UserRepository,
    private val roomRepo: ChatRoomRepository
) {
    fun saveMessage(dto: ChatMessageDto) {
        val from = userRepo.findById(dto.fromUserId).orElseThrow()
        val to = dto.toUserId?.let { userRepo.findById(it).orElseThrow() }
        val room = dto.roomId?.let { roomRepo.findById(it).orElseThrow() }
        val timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(dto.timestamp), ZoneId.systemDefault())
        val msg = Message(fromUser = from, toUser = to, room = room,
            content = dto.content, timestamp = timestamp)
        messageRepo.save(msg)
    }
}