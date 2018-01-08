package com.altran.towncodex.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.altran.towncodex.BaseActivity
import com.altran.towncodex.R
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class DetailActivity : BaseActivity() {

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar
}
