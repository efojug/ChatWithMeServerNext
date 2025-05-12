package com.efojug.chatwithmeservernext.service

import com.efojug.chatwithmeservernext.dto.UserLoginDto
import com.efojug.chatwithmeservernext.dto.UserRegisterDto
import com.efojug.chatwithmeservernext.dto.UserProfileUpdateDto
import com.efojug.chatwithmeservernext.repository.UserRepository
import com.efojug.chatwithmeservernext.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration}") private val jwtExpiration: Long
) {
    private val encoder = BCryptPasswordEncoder()

    fun register(dto: UserRegisterDto) {
        val hash = encoder.encode(dto.password)
        val user = User(username = dto.username, passwordHash = hash,
            nickname = dto.username, avatarUrl = null, backgroundUrl = null)
        userRepository.save(user)
    }

    fun login(dto: UserLoginDto): String {
        val user = userRepository.findByUsername(dto.username)
            ?: throw RuntimeException("用户不存在")
        if (!encoder.matches(dto.password, user.passwordHash)) throw RuntimeException("密码错误")
        // 生成 JWT
        val now = Date()
        val expiry = Date(now.time + jwtExpiration)
        return Jwts.builder()
            .setSubject(user.id.toString())
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getProfile(userId: Long): User {
        return userRepository.findById(userId).orElseThrow()
    }

    fun updateProfile(userId: Long, dto: UserProfileUpdateDto) {
        val user = userRepository.findById(userId).orElseThrow()
        user.nickname = dto.nickname
        user.avatarUrl = dto.avatarUrl
        user.backgroundUrl = dto.backgroundUrl
        userRepository.save(user)
    }
}