package com.altran.towncodex.di

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule {
    // same as provides but this returns injected parameter
    @Provides
    @Singleton
    fun bindContext(application: Application): Context = application
}
