package com.efojug.chatwithmeservernext.controller

import com.efojug.chatwithmeservernext.dto.UserProfileUpdateDto
import com.efojug.chatwithmeservernext.entity.User
import com.efojug.chatwithmeservernext.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/profile")
class ProfileController(
    private val userService: UserService
) {
    @GetMapping
    fun getProfile(principal: java.security.Principal): User {
        return userService.getProfile(principal.name.toLong())
    }

    @PutMapping
    fun updateProfile(
        @RequestBody dto: UserProfileUpdateDto,
        principal: java.security.Principal
    ): ResponseEntity<Any> {
        userService.updateProfile(principal.name.toLong(), dto)
        return ResponseEntity.ok().build()
    }
}