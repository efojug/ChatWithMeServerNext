package com.efojug.chatwithmeservernext.controller

import com.efojug.chatwithmeservernext.dto.UserLoginDto
import com.efojug.chatwithmeservernext.dto.UserRegisterDto
import com.efojug.chatwithmeservernext.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun register(@RequestBody dto: UserRegisterDto): ResponseEntity<Any> {
        userService.register(dto)
        return ResponseEntity.ok(mapOf("message" to "注册成功"))
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: UserLoginDto): ResponseEntity<Any> {
        val token = userService.login(dto)
        return ResponseEntity.ok(mapOf("token" to token))
    }
}