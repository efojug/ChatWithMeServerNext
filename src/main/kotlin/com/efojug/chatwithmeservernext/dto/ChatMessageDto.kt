package com.efojug.chatwithmeservernext.dto

data class ChatMessageDto(
    val fromUserId: Long,
    val toUserId: Long?,
    val roomId: Long?,
    val content: String,
    val timestamp: Long,
    val type: String = "TEXT"
)