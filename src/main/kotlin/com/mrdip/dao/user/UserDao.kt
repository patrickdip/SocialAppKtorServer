package com.mrdip.dao.user

import com.mrdip.model.SignUpParams
import com.mrdip.model.User

interface UserDao {
    suspend fun insert(params: SignUpParams): User?
    suspend fun findByEmail(email: String): User?
}