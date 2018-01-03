package com.altran.towncodex.main

import com.altran.towncodex.model.Inhabitant

class MainPresenter : MainContract.Presenter, MainContract.InteractorOutput {
    override fun listItemClicked(inhabitant: Inhabitant?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewCreated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQuerySuccess(data: List<Inhabitant>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
