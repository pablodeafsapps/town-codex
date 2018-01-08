package com.altran.towncodex.di

import com.altran.towncodex.main.MainActivity
import com.altran.towncodex.main.MainContract
import com.altran.towncodex.main.MainInteractor
import com.altran.towncodex.main.MainPresenter

import dagger.Binds
import dagger.Module

@Module
abstract class MainAbstractModule {
    // "@Binds" substitute "@Provides"
    // Inject interfaces (instead of the class) using the class object which implements it
    @Binds
    abstract fun bindMainView(mainActivity: MainActivity): MainContract.View

    @Binds
    abstract fun bindMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun bindMainInteractor(mainInteractor: MainInteractor): MainContract.Interactor
}
