package com.mrdip.di

import com.mrdip.dao.follows.FollowsDao
import com.mrdip.dao.follows.FollowsDaoImpl
import com.mrdip.dao.user.UserDao
import com.mrdip.dao.user.UserDaoImpl
import com.mrdip.repository.auth.AuthRepository
import com.mrdip.repository.auth.AuthRepositoryImpl
import com.mrdip.repository.follows.FollowsRepository
import com.mrdip.repository.follows.FollowsRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
    single<FollowsDao> { FollowsDaoImpl() }
    single<FollowsRepository> { FollowsRepositoryImpl(get(), get()) }
}