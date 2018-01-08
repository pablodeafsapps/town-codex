package com.altran.towncodex.detail

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.model.Inhabitant

import ru.terrakok.cicerone.Router

import javax.inject.Inject

class DetailPresenter @Inject constructor() : DetailContract.Presenter {

    @Inject
    lateinit var view: DetailContract.View
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated(inhabitant: Inhabitant) {
        view.showInhabitantData(inhabitant)
    }

    override fun backButtonClicked() {
        router?.exit()
    }
}