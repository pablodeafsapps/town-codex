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
        val DEFAULT_TOWN_NAME = "Brastlewark"
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

    override fun onViewCreated() {
        view.showLoading()
        interactor.loadInhabitantsList { queryResult ->
            when (queryResult) {
                is Result.Failure -> {
                    Toast.makeText(view as Activity, "Unable to load data", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    queryResult.component1()?.let {
                        this.parseJsonResponse(it, DEFAULT_TOWN_NAME)
                    }?.let {
                        view.hideLoading()
                        view.publishDataList(it)
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
