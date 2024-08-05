package com.abcapp.di

import com.abcapp.data.FundingProjectRepositoryImpl
import com.abcapp.domain.FundingProjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindFundingProjectRepository(
        impl: FundingProjectRepositoryImpl
    ): FundingProjectRepository
}