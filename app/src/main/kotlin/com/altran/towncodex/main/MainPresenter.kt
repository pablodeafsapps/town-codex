package com.altran.towncodex.main

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.di.DaggerAppComponent
import com.altran.towncodex.model.Inhabitant

import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter, MainContract.InteractorOutput {

    @Inject
    lateinit var view: MainContract.View
    @Inject
    lateinit var interactor: MainContract.Interactor

    init {
        DaggerAppComponent.builder().application(BaseApplication.INSTANCE).build().inject(this)
    }

    override fun listItemClicked(inhabitant: Inhabitant?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated() {
        view.showLoading()
        interactor.setOutputEntity(this)
    }

    override fun onQuerySuccess(data: List<Inhabitant>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
