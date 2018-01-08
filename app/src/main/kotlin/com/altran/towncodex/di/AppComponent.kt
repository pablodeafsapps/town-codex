package com.altran.towncodex.di

import android.app.Application

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.main.MainContract

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        MainModule::class,
        MainAbstractModule::class,
        DetailAbstractModule::class,
        ActivityBuilderModule::class))
interface AppComponent {

    fun inject(app: BaseApplication)
    fun inject(presenter: MainContract.Presenter)
    fun inject(interactor: MainContract.Interactor)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
