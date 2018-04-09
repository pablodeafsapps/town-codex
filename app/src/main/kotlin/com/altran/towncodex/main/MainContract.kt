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
        fun showNoDataWarning(show: Boolean)
        fun enableSearchBox()
        fun disableSearchBox()
        fun scrollToTop()
        fun goBack()
    }

    interface Presenter {
        // User actions
        fun listItemClicked(inhabitant: Inhabitant?)
        fun searchOptionClicked(isVisible: Boolean)
        fun hwBackButtonClicked(isVisible: Boolean)
        fun searchBoxUpdated(filterTerm: String)
        // Model updates
        fun onViewCreated(filterTerm: String)
    }

    interface Interactor {
        fun loadInhabitantsList(result: (Result<String, FuelError>) -> Unit)
    }
}
