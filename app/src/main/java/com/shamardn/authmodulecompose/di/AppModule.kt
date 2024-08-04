package com.shamardn.authmodulecompose.di

import android.app.Application
import android.content.Context
import com.shamardn.authmodulecompose.data.repository.AuthRepositoryImpl
import com.shamardn.authmodulecompose.domain.repository.AuthRepository
import com.shamardn.authmodulecompose.domain.use_case.ValidateLoginInputUseCase
import com.shamardn.authmodulecompose.domain.use_case.ValidateRegisterInputUseCase
import com.shamardn.authmodulecompose.util.ResourceStringProvider
import com.shamardn.authmodulecompose.util.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase(): ValidateLoginInputUseCase {
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase(): ValidateRegisterInputUseCase {
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideStringProvider(@ApplicationContext context: Context): StringProvider {
        return ResourceStringProvider(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }
}