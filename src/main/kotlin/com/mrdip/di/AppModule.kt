package com.mrdip.di

import com.mrdip.dao.follows.FollowsDao
import com.mrdip.dao.follows.FollowsDaoImpl
import com.mrdip.dao.post.PostDao
import com.mrdip.dao.post.PostDaoImpl
import com.mrdip.dao.post_comments.PostCommentsDao
import com.mrdip.dao.post_comments.PostCommentsDaoImpl
import com.mrdip.dao.post_likes.PostLikesDao
import com.mrdip.dao.post_likes.PostLikesDaoImpl
import com.mrdip.dao.user.UserDao
import com.mrdip.dao.user.UserDaoImpl
import com.mrdip.repository.auth.AuthRepository
import com.mrdip.repository.auth.AuthRepositoryImpl
import com.mrdip.repository.follows.FollowsRepository
import com.mrdip.repository.follows.FollowsRepositoryImpl
import com.mrdip.repository.post.PostRepository
import com.mrdip.repository.post.PostRepositoryImpl
import com.mrdip.repository.post_comments.PostCommentsRepository
import com.mrdip.repository.post_comments.PostCommentsRepositoryImpl
import com.mrdip.repository.post_likes.PostLikesRepository
import com.mrdip.repository.post_likes.PostLikesRepositoryImpl
import com.mrdip.repository.profile.ProfileRepository
import com.mrdip.repository.profile.ProfileRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
    single<FollowsDao> { FollowsDaoImpl() }
    single<FollowsRepository> { FollowsRepositoryImpl(get(), get()) }
    single<PostLikesDao> { PostLikesDaoImpl() }
    single<PostDao> { PostDaoImpl()}
    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
    single<PostCommentsDao> { PostCommentsDaoImpl() }
    single<PostCommentsRepository> { PostCommentsRepositoryImpl(get(), get()) }
    single<PostLikesRepository> { PostLikesRepositoryImpl(get(), get()) }
}