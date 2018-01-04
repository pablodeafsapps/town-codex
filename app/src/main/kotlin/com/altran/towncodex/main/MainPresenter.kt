package com.altran.towncodex.main

import android.app.Activity
import android.widget.Toast
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
        interactor.loadInhabitantsList()
    }

    override fun onQuerySuccess(data: List<Inhabitant>) {
        Toast.makeText(view as Activity, "Success!!", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryError() {
        Toast.makeText(view as Activity, "Error!!", Toast.LENGTH_SHORT).show()
    }
}
