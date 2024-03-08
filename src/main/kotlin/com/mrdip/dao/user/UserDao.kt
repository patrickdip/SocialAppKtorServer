package com.mrdip.dao.user

import com.mrdip.model.SignUpParams

interface UserDao {
    suspend fun insert(params: SignUpParams): UserRow?
    suspend fun findByEmail(email: String): UserRow?

    suspend fun findById(userId: Long): UserRow?

    suspend fun updateUser(userId: Long, name: String, bio: String, imageUrl: String?): Boolean

    suspend fun updateFollowsCount(follower: Long, following: Long, isFollowing: Boolean): Boolean

    suspend fun getUsers(ids: List<Long>): List<UserRow>

    suspend fun getPopularUsers(limit: Int): List<UserRow>
}