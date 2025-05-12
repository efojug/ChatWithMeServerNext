package com.efojug.chatwithmeservernext.controller

import com.efojug.chatwithmeservernext.dto.FriendAcceptDto
import com.efojug.chatwithmeservernext.dto.FriendRequestDto
import com.efojug.chatwithmeservernext.service.FriendService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/friends")
class FriendController(
    private val friendService: FriendService
) {
    @PostMapping("/request")
    fun sendRequest(
        @RequestBody dto: FriendRequestDto,
        principal: java.security.Principal
    ): ResponseEntity<Any> {
        friendService.sendRequest(principal.name.toLong(), dto.toUserId)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/accept")
    fun acceptRequest(
        @RequestBody dto: FriendAcceptDto
    ): ResponseEntity<Any> {
        friendService.acceptRequest(dto)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/list")
    fun listFriends(principal: java.security.Principal): ResponseEntity<Any> {
        val list = friendService.listFriends(principal.name.toLong())
        return ResponseEntity.ok(list)
    }
}