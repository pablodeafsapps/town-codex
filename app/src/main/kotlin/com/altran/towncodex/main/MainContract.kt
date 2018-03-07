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
        fun showNoDataWarning()
        fun enableSearchBox()
        fun disableSearchBox()
        fun goBack()
    }

    interface Presenter {
        // User actions
        fun listItemClicked(inhabitant: Inhabitant?)
        fun searchOptionClicked(isVisible: Boolean)
        fun hwBackButtonClicked(isVisible: Boolean)
        fun searchBoxUpdated(searchTerm: String)
        // Model updates
        fun onViewCreated()
    }

    interface Interactor {
        fun loadInhabitantsList(result: (Result<String, FuelError>) -> Unit)
        fun filterInhabitantList(searchTerm: String, result: (resultList: List<Inhabitant>) -> Unit)
    }
}
