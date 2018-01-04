package com.altran.towncodex.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast

import com.altran.towncodex.BaseActivity
import com.altran.towncodex.R
import com.altran.towncodex.model.Inhabitant

import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val recyclerView: RecyclerView by lazy { rv_inhabitants_list_activity_main }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.adapter = InhabitantsListAdapter({ inhabitant -> presenter.listItemClicked(inhabitant) }, null)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewCreated()
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun showLoading() {
        Toast.makeText(this, "Show loading...", Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun publishDataList(data: List<Inhabitant>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInfoMessage(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
