package com.abc.app.di

import com.abc.app.data.FundingProjectRepositoryImpl
import com.abc.app.domain.FundingProjectRepository
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