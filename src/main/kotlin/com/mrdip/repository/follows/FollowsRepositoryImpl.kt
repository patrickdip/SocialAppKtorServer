package com.mrdip.repository.follows

import com.mrdip.dao.follows.FollowsDao
import com.mrdip.dao.user.UserDao
import com.mrdip.dao.user.UserRow
import com.mrdip.model.FollowAndUnfollowResponse
import com.mrdip.model.FollowUserData
import com.mrdip.model.GetFollowsResponse
import com.mrdip.util.Constants
import com.mrdip.util.Response
import io.ktor.http.*

class FollowsRepositoryImpl(
    private val userDao: UserDao,
    private val followDao: FollowsDao
) : FollowsRepository {
    override suspend fun followUser(follower: Long, following: Long): Response<FollowAndUnfollowResponse> {
        return if(followDao.isAlreadyFollowing(follower, following)){
            Response.Error(
                code = HttpStatusCode.Forbidden,
                data = FollowAndUnfollowResponse(
                    success = false,
                    message = "You are already following this user"
                )
            )
        }else{
            val success = followDao.followUser(follower, following)

            if (success){
                userDao.updateFollowsCount(follower, following, isFollowing = true)
                Response.Success(
                    data = FollowAndUnfollowResponse(success = true)
                )
            }else{
                Response.Error(
                    code = HttpStatusCode.InternalServerError,
                    data = FollowAndUnfollowResponse(
                        success = false,
                        message = "Oops, something went wrong on our side, please try again!"
                    )
                )
            }
        }
    }

    override suspend fun unfollowUser(follower: Long, following: Long): Response<FollowAndUnfollowResponse> {
        val success = followDao.unfollowUser(follower, following)

        return if (success){
            userDao.updateFollowsCount(follower, following, isFollowing = false)
            Response.Success(
                data = FollowAndUnfollowResponse(success = true)
            )
        }else{
            Response.Error(
                code = HttpStatusCode.InternalServerError,
                data = FollowAndUnfollowResponse(
                    success = false,
                    message = "Oops, something went wrong on our side, please try again!"
                )
            )
        }
    }

    override suspend fun getFollowers(userId: Long, pageNumber: Int, pageSize: Int): Response<GetFollowsResponse> {
        val followersIds = followDao.getFollowers(userId, pageNumber, pageSize)
        val followersRows = userDao.getUsers(ids = followersIds)
        val followers = followersRows.map { followerRow ->
            val isFollowing = followDao.isAlreadyFollowing(follower = userId, following = followerRow.id)
            toFollowUserData(userRow = followerRow, isFollowing = isFollowing)
        }
        return Response.Success(
            data = GetFollowsResponse(success = true, follows = followers)
        )
    }

    override suspend fun getFollowing(userId: Long, pageNumber: Int, pageSize: Int): Response<GetFollowsResponse> {
        val followingIds = followDao.getFollowing(userId, pageNumber, pageSize)
        val followingRows = userDao.getUsers(ids = followingIds)
        val following = followingRows.map { followingRow ->
            toFollowUserData(userRow = followingRow, isFollowing = true)
        }
        return Response.Success(
            data = GetFollowsResponse(success = true, follows = following)
        )
    }

    override suspend fun getFollowingSuggestions(userId: Long): Response<GetFollowsResponse> {
        val hasFollowing = followDao.getFollowing(userId = userId, pageNumber = 0, pageSize = 1).isNotEmpty()
        return if (hasFollowing){
            Response.Error(
                code = HttpStatusCode.Forbidden,
                data = GetFollowsResponse(success = false, message = "User has following")
            )
        }else {
            val suggestedFollowingRows = userDao.getPopularUsers(limit = Constants.SUGGESTED_FOLLOWING_LIMIT)
            val suggestedFollowing = suggestedFollowingRows.filterNot {
                it.id == userId
            }.map {
                toFollowUserData(userRow = it, isFollowing = false)
            }
            return Response.Success(
                data = GetFollowsResponse(success = true, follows = suggestedFollowing)
            )
        }
    }

    private fun toFollowUserData(userRow: UserRow, isFollowing: Boolean): FollowUserData{
        return FollowUserData(
            id = userRow.id,
            name = userRow.name,
            bio = userRow.bio,
            imageUrl = userRow.imageUrl,
            isFollowing = isFollowing
        )
    }
}






















