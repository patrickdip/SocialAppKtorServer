package com.mrdip.repository.user

import com.mrdip.model.AuthResponse
import com.mrdip.model.SignInParams
import com.mrdip.model.SignUpParams
import com.mrdip.util.Response

interface UserRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}