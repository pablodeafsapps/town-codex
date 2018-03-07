package com.altran.towncodex.main

import android.app.Activity
import android.widget.Toast

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.detail.DetailActivity
import com.altran.towncodex.di.DaggerAppComponent
import com.altran.towncodex.model.Inhabitant
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.json.JSONObject

import ru.terrakok.cicerone.Router

import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter {

    companion object {
        const val DEFAULT_TOWN_NAME = "Brastlewark"
        const val NO_FILTER = ".*"
    }

    @Inject
    lateinit var view: MainContract.View
    @Inject
    lateinit var interactor: MainContract.Interactor
    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    init {
        DaggerAppComponent.builder().application(BaseApplication.INSTANCE).build().inject(this)
    }

    override fun listItemClicked(inhabitant: Inhabitant?) {
        router?.navigateTo(DetailActivity.TAG, inhabitant)
    }

    override fun searchOptionClicked(isVisible: Boolean) {
        if (!isVisible) {
            view.enableSearchBox()
        }
    }

    override fun hwBackButtonClicked(isVisible: Boolean) {
        if (isVisible) {
            view.disableSearchBox()
        } else {
            view.goBack()
        }
    }

    override fun searchBoxUpdated(filterTerm: String) {
        view.showLoading()
        this.loadAndFilterList(filterTerm)
        view.scrollToTop()
    }

    override fun onViewCreated(filterTerm: String) {
        view.showLoading()
        this.loadAndFilterList(when (filterTerm.isEmpty()) {
            true -> NO_FILTER
            false -> filterTerm
        })
    }

    private fun loadAndFilterList(key: String) {
        interactor.loadInhabitantsList { queryResult ->
            when (queryResult) {
                is Result.Failure -> {
                    Toast.makeText(view as Activity, "Unable to load data", Toast.LENGTH_SHORT).show()
                    view.hideLoading()
                    view.showNoDataWarning(true)
                }
                is Result.Success -> {
                    queryResult.component1()?.let {
                        this.parseJsonResponse(it, DEFAULT_TOWN_NAME)
                    }?.let {
                        view.apply {
                            hideLoading()
                            val filteredList = it.filter {
                                it.name.contains(Regex(key, RegexOption.IGNORE_CASE))
                            }

                            publishDataList(filteredList)
                            showNoDataWarning(filteredList.isEmpty())
                        }
                    }
                }
            }
        }
    }

    private fun parseJsonResponse(resultString: String?, townName: String): List<Inhabitant>? {
        val inhabitantsJsonObject = JSONObject(resultString)
        val type = object : TypeToken<List<Inhabitant>>() {}.type

        return Gson().fromJson(inhabitantsJsonObject.getJSONArray(townName).toString(), type)
    }
}
