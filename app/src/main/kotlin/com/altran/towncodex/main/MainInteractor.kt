package com.altran.towncodex.main

import javax.inject.Inject

class MainInteractor @Inject constructor() : MainContract.Interactor {

    lateinit var output: MainContract.InteractorOutput

    override fun loadInhabitantsList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setOutputEntity(interactorOutput: MainContract.InteractorOutput) {
        output = interactorOutput
    }
}
