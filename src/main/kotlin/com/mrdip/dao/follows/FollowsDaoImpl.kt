package com.mrdip.dao.follows

import com.mrdip.dao.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class FollowsDaoImpl : FollowsDao {
    override suspend fun followUser(follower: Long, following: Long): Boolean {
        return dbQuery{
            val insertStatement = FollowsTable.insert {
                it[followerId] = follower
                it[followingId] = following
            }

            insertStatement.resultedValues?.singleOrNull() != null
        }
    }

    override suspend fun unfollowUser(follower: Long, following: Long): Boolean {
        return dbQuery {
            FollowsTable.deleteWhere {
                (followerId eq follower) and (followingId eq following)
            } > 0
        }
    }

    override suspend fun getFollowers(userId: Long, pageNumber: Int, pageSize: Int): List<Long> {
        return dbQuery {
            FollowsTable.select {
                FollowsTable.followingId eq userId
            }
                .orderBy(FollowsTable.followDate, SortOrder.DESC)
                .limit(n = pageSize, offset = ((pageNumber -1) * pageSize).toLong())
                .map { it[FollowsTable.followerId] }
        }
    }

    override suspend fun getFollowing(userId: Long, pageNumber: Int, pageSize: Int): List<Long> {
        return dbQuery {
            FollowsTable.select {
                FollowsTable.followerId eq userId
            }
                .orderBy(FollowsTable.followDate, SortOrder.DESC)
                .limit(n = pageSize, offset = ((pageNumber -1) * pageSize).toLong())
                .map { it[FollowsTable.followingId] }
        }
    }

    override suspend fun getAllFollowing(userId: Long): List<Long> {
        return dbQuery {
            FollowsTable
                .select { FollowsTable.followerId eq userId }
                .map { it[FollowsTable.followingId] }
        }
    }

    override suspend fun isAlreadyFollowing(follower: Long, following: Long): Boolean {
        return dbQuery {
            val queryResult = FollowsTable.select {
                (FollowsTable.followerId eq follower) and (FollowsTable.followingId eq following)
            }
            queryResult.toList().isNotEmpty()
        }
    }
}









