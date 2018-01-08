package com.altran.towncodex

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = "BaseActivity"
    }

    override fun onResume() {
        super.onResume()

        this.getToolbarInstance()?.let { this.initView(it) }
    }

    private fun initView(toolbar: Toolbar) {
        // Toolbar setup
        setSupportActionBar(toolbar)   // Replaces the 'ActionBar' with the 'Toolbar'
    }

    abstract fun getToolbarInstance(): Toolbar?
}
