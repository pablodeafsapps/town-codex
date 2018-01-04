package com.altran.towncodex.di

import dagger.Module
import dagger.Provides

import javax.inject.Named

@Module
class MainModule {
    @Provides
    @Named("name")
    fun provideUrl(): String = "https://raw.githubusercontent.com/rrafols/mobile_test/master/data.json"
}
