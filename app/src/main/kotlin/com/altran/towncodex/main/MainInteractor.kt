package com.altran.towncodex.main

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.di.DaggerAppComponent
import com.altran.towncodex.model.Inhabitant

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor() : MainContract.Interactor {

    private lateinit var output: MainContract.InteractorOutput
    @Inject
    @field:Named("dataUrl")
    lateinit var url: String

    init {
        DaggerAppComponent.builder().application(BaseApplication.INSTANCE).build().inject(this)
    }

    override fun loadInhabitantsList() {
        url.httpGet().responseJson { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    output.onQueryError()
                }
                is Result.Success -> {
                    val inhabitantsJsonObject = result.get().obj()

                    val type = object : TypeToken<List<Inhabitant>>() {}.type
                    val inhabitantsList: List<Inhabitant> =
                            Gson().fromJson(inhabitantsJsonObject.getJSONArray("Brastlewark").toString(), type)

                    output.onQuerySuccess(inhabitantsList)
                }
            }
        }

    }

    override fun setOutputEntity(interactorOutput: MainContract.InteractorOutput) {
        output = interactorOutput
    }
}
