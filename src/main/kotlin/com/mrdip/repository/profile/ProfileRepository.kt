package com.mrdip.repository.profile

import com.mrdip.model.ProfileResponse
import com.mrdip.model.UpdateUserParams
import com.mrdip.util.Response

interface ProfileRepository {

    suspend fun getUserById(userId: Long, currentUserId: Long): Response<ProfileResponse>

    suspend fun updateUser(updateUserParams: UpdateUserParams): Response<ProfileResponse>
}