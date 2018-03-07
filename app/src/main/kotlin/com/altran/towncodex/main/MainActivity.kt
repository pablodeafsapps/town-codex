package com.altran.towncodex.main

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import com.altran.towncodex.BaseActivity
import com.altran.towncodex.BaseApplication
import com.altran.towncodex.R
import com.altran.towncodex.detail.DetailActivity
import com.altran.towncodex.model.Inhabitant
import com.jakewharton.rxbinding2.widget.RxTextView

import dagger.android.AndroidInjection

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    companion object {
        const val TAG = "MainActivity"
    }

    private val mainNavigator: Navigator? by lazy {
        object : Navigator {

            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    DetailActivity.TAG -> {
                        val data = (command.transitionData as Inhabitant)
                        startActivity(Intent(this@MainActivity, DetailActivity::class.java)
                                .putExtra("inhabitant", data as Parcelable))
                    }
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }
    @Inject
    lateinit var presenter: MainContract.Presenter
    private val progressBar: ProgressBar by lazy { prog_bar_loading_jokes_activity_main }
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val searchBox: EditText by lazy { et_search_box_toolbar_view }
    private val recyclerView: RecyclerView by lazy { rv_inhabitants_list_activity_main }
    private val tvNoData: TextView by lazy { tv_no_data_warning_activity_main }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = InhabitantsListAdapter({ inhabitant -> presenter.listItemClicked(inhabitant) }, null)

        RxTextView.textChanges(searchBox)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter { it.length > 2 || it.isEmpty() }
                .map { charSeq -> charSeq.toString() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { str ->
                    presenter.searchBoxUpdated(str)
                }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewCreated(searchBox.text.toString())
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(mainNavigator)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun getMenuResource(): Int? = R.menu.menu_options_main_activity

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

    override fun showNoDataWarning(show: Boolean) {
        tvNoData.visibility = when (show) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun enableSearchBox() {
        searchBox.visibility = View.VISIBLE
    }

    override fun disableSearchBox() {
        searchBox.visibility = View.GONE
    }

    override fun scrollToTop() {
        recyclerView.layoutManager.smoothScrollToPosition(recyclerView, null, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =
            when (item?.itemId) {
                R.id.item_browse_menu_options_main_activity -> {
                    Log.d(TAG, "search clicked")
                    presenter.searchOptionClicked(searchBox.visibility == View.VISIBLE)
                    true
                }
                else -> {
                    Log.d(TAG, "anything clicked")
                    super.onOptionsItemSelected(item)
                }
            }

    override fun onBackPressed() {
        presenter.hwBackButtonClicked(searchBox.visibility == View.VISIBLE)
    }

    override fun goBack() {
        super.onBackPressed()
    }
}
