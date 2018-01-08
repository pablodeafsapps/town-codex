package com.altran.towncodex.di

import com.altran.towncodex.detail.DetailActivity
import com.altran.towncodex.detail.DetailContract
import com.altran.towncodex.detail.DetailPresenter

import dagger.Binds
import dagger.Module

@Module
abstract class DetailAbstractModule {

    @Binds
    abstract fun bindDetailView(detailActivity: DetailActivity): DetailContract.View

    @Binds
    abstract fun bindMainPresenter(detailPresenter: DetailPresenter): DetailContract.Presenter
}
