package com.mrdip.repository.auth

import com.mrdip.model.AuthResponse
import com.mrdip.model.SignInParams
import com.mrdip.model.SignUpParams
import com.mrdip.util.Response

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}