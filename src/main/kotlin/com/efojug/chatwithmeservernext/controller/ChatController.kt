package com.efojug.chatwithmeservernext.controller

import com.efojug.chatwithmeservernext.dto.ChatMessageDto
import com.efojug.chatwithmeservernext.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val chatService: ChatService
) {
    @MessageMapping("/chat.send")
    fun sendMessage(@Payload message: ChatMessageDto) {
        chatService.saveMessage(message)
        if (message.toUserId != null) {
            simpMessagingTemplate.convertAndSendToUser(
                message.toUserId.toString(), "/queue/messages", message
            )
        } else if (message.roomId != null) {
            simpMessagingTemplate.convertAndSend(
                "/topic/room.${message.roomId}", message
            )
        }
    }
}