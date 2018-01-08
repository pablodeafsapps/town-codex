package com.altran.towncodex.main

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

import com.altran.towncodex.BaseActivity
import com.altran.towncodex.BaseApplication
import com.altran.towncodex.R
import com.altran.towncodex.detail.DetailActivity
import com.altran.towncodex.model.Inhabitant

import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    private val navigator: Navigator? by lazy {
        object : Navigator {

            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val data = (command.transitionData as Inhabitant)

                when (command.screenKey) {
                    DetailActivity.TAG -> startActivity(Intent(this@MainActivity, DetailActivity::class.java)
                            .putExtra("inhabitant", data as Parcelable))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    @Inject
    lateinit var presenter: MainContract.Presenter
    private val progressBar: ProgressBar by lazy { prog_bar_loading_jokes_activity_main }
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val recyclerView: RecyclerView by lazy { rv_inhabitants_list_activity_main }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = InhabitantsListAdapter({ inhabitant -> presenter.listItemClicked(inhabitant) }, null)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewCreated()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun publishDataList(data: List<Inhabitant>) {
        (recyclerView.adapter as InhabitantsListAdapter).updateData(data)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
