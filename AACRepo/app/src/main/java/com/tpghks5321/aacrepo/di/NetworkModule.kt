package com.tpghks5321.aacrepo.di

import com.tpghks5321.aacrepo.api.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAppService(): AppService {
        return AppService.create()
    }

}