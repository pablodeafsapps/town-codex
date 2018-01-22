package com.altran.towncodex

import android.support.annotation.MenuRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater

abstract class BaseActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        this.getToolbarInstance()?.let { this.initView(it) }
    }

    private fun initView(toolbar: Toolbar) {
        // Toolbar setup
        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
    }

    abstract fun getToolbarInstance(): Toolbar?

    @MenuRes
    abstract fun getMenuResource(): Int?

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = this.getMenuResource()?.let {
            MenuInflater(this).inflate(it, menu)
            true
        } ?: super.onCreateOptionsMenu(menu)
}
