package com.efojug.chatwithmeservernext.dto

data class UserProfileUpdateDto(
    val nickname: String,
    val avatarUrl: String?,
    val backgroundUrl: String?
)