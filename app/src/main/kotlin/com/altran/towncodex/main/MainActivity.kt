package com.altran.towncodex.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar

import com.altran.towncodex.BaseActivity
import com.altran.towncodex.R
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class MainActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val recyclerView: RecyclerView by lazy { rv_inhabitants_list_activity_main }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.adapter = InhabitantsListAdapter({ inhabitant -> presenter.listItemClicked(inhabitant) }, null)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar
}
