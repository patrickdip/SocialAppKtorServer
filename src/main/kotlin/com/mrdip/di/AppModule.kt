package com.mrdip.di

import com.mrdip.dao.user.UserDao
import com.mrdip.dao.user.UserDaoImpl
import com.mrdip.repository.user.UserRepository
import com.mrdip.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<UserDao> { UserDaoImpl() }
}