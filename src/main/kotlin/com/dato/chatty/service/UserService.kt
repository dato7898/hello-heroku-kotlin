package com.dato.chatty.service

import com.dato.chatty.model.User
import com.dato.chatty.repo.UserRepo
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userRepo: UserRepo
) {

    fun findByEmail(email: String): Optional<User> {
        return userRepo.findByEmail(email)
    }

    fun update(user: User) {
        userRepo.save(user)
    }

    fun findFriends(email: String): List<User> {
        val user = userRepo.findByEmail(email).orElseThrow{ RuntimeException() }
        return userRepo.findAllByIdInAndFriendIds(user.friendIds, user.id)
    }

    fun findFriendsById(userId: String): List<User> {
        val user = userRepo.findById(userId).orElseThrow{ RuntimeException() }
        return userRepo.findAllByIdInAndFriendIds(user.friendIds, user.id)
    }

}