package com.sary.assessment.di

import com.sary.assessment.data.MainRepositoryImpl
import com.sary.assessment.domain.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

  @Binds
  abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}