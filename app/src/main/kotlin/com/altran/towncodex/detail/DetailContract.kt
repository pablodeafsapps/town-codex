package com.altran.towncodex.detail

import com.altran.towncodex.model.Inhabitant

interface DetailContract {
    interface View {
        fun showInhabitantData(inhabitant: Inhabitant)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        // User actions
        fun backButtonClicked()
        // Model updates
        fun onViewCreated(inhabitant: Inhabitant)
    }
}
