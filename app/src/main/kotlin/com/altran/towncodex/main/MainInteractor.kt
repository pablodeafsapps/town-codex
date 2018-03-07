package com.altran.towncodex.main

import com.altran.towncodex.BaseApplication
import com.altran.towncodex.di.DaggerAppComponent
import com.altran.towncodex.model.Inhabitant
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_responseString
import com.github.kittinunf.result.Result

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor() : MainContract.Interactor {

    @Inject
    @field:Named("dataUrl")
    lateinit var url: String

    init {
        DaggerAppComponent.builder().application(BaseApplication.INSTANCE).build().inject(this)
    }

    override fun filterInhabitantList(searchTerm: String, result: (resultList: List<Inhabitant>) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInhabitantsList(result: (Result<String, FuelError>) -> Unit) {
        url.httpGet().rx_responseString()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { res ->
                    result(res.second)
                }
    }
}
