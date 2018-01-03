package com.altran.towncodex.main

import com.altran.towncodex.model.Inhabitant

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishDataList(data: List<Inhabitant>)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun listItemClicked(inhabitant: Inhabitant?)
        // Model updates
        fun onViewCreated()
    }

    interface Interactor {
        fun loadInhabitantsList()
        fun setOutputEntity(interactorOutput: MainContract.InteractorOutput)
    }

    interface InteractorOutput {
        fun onQuerySuccess(data: List<Inhabitant>)
        fun onQueryError()
    }
}
