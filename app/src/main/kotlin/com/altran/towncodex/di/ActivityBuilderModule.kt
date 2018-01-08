package com.altran.towncodex.di

import com.altran.towncodex.detail.DetailActivity
import com.altran.towncodex.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(MainAbstractModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailAbstractModule::class))
    internal abstract fun bindDetailActivity(): DetailActivity
}
