package com.altran.towncodex.main

import com.altran.towncodex.model.Inhabitant
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result

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
        fun loadInhabitantsList(result: (Result<String, FuelError>) -> Unit)
    }
}
