package com.altran.towncodex.detail

/**
 * Created by pablo on 8/01/18.
 */
interface DetailContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions

        // Model updates
        fun onViewCreated()
    }
}