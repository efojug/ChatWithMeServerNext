package com.efojug.chatwithmeservernext.service

import com.efojug.chatwithmeservernext.dto.FriendAcceptDto
import com.efojug.chatwithmeservernext.entity.Friend
import com.efojug.chatwithmeservernext.entity.FriendRequest
import com.efojug.chatwithmeservernext.entity.User
import com.efojug.chatwithmeservernext.repository.FriendRepository
import com.efojug.chatwithmeservernext.repository.FriendRequestRepository
import com.efojug.chatwithmeservernext.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class FriendService(
    private val userRepository: UserRepository,
    private val friendRequestRepo: FriendRequestRepository,
    private val friendRepo: FriendRepository
) {
    /**
     * 发送好友请求
     */
    fun sendRequest(fromUserId: Long, toUserId: Long) {
        val from = userRepository.findById(fromUserId)
            .orElseThrow { RuntimeException("用户 $fromUserId 不存在") }
        val to = userRepository.findById(toUserId)
            .orElseThrow { RuntimeException("用户 $toUserId 不存在") }

        val req = FriendRequest(
            fromUser = from,
            toUser = to,
            status = "PENDING"
        )
        friendRequestRepo.save(req)
    }

    /**
     * 接受好友请求，并创建双向好友关系
     */
    fun acceptRequest(dto: FriendAcceptDto) {
        val req = friendRequestRepo.findById(dto.requestId)
            .orElseThrow { RuntimeException("请求 ${dto.requestId} 不存在") }

        // 更新请求状态
        req.status = "ACCEPTED"
        friendRequestRepo.save(req)

        // 获取非空的用户 ID
        val fromId = req.fromUser?.id
            ?: throw RuntimeException("请求的发送者信息不完整")
        val toId = req.toUser?.id
            ?: throw RuntimeException("请求的接收者信息不完整")

        // 创建双向关系
        friendRepo.save(Friend(userId = fromId, friendId = toId))
        friendRepo.save(Friend(userId = toId, friendId = fromId))
    }

    /**
     * 列出好友列表
     */
    fun listFriends(userId: Long): List<User> {
        // 查询所有与 userId 有关系的 Friend 记录
        val relations = friendRepo.findByUserId(userId)
        // 根据 friendId 查出 User 实体
        return relations.map { relation ->
            userRepository.findById(relation.friendId)
                .orElseThrow { RuntimeException("好友 ${relation.friendId} 不存在") }
        }
    }
}
