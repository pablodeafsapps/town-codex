package com.altran.towncodex.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.*

import com.altran.towncodex.BaseActivity
import com.altran.towncodex.BaseApplication
import com.altran.towncodex.R
import com.altran.towncodex.model.Inhabitant
import com.squareup.picasso.Picasso

import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.listview_header.view.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command

import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View {

    companion object {
        val TAG = "DetailActivity"
    }

    private val detailNavigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Back) {
                    back()
                }
            }

            private fun back() {
                finish()
            }
        }
    }
    @Inject
    lateinit var presenter: DetailContract.Presenter
    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }
    private val ivSnapshot: ImageView by lazy { iv_snapshot_activity_detail }
    private val tvName: TextView by lazy { tv_name_activity_detail }
    private val tvAge: TextView by lazy { tv_age_activity_detail }
    private val tvWeight: TextView by lazy { tv_weight_activity_detail }
    private val tvHeight: TextView by lazy { tv_height_activity_detail }
    private val tvHaircolor: TextView by lazy { tv_haircolor_activity_detail }
    private val lvProfessions: ListView by lazy { lv_professions_activity_detail }
    private val lvFriends: ListView by lazy { lv_friends_activity_detail }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        layoutInflater.apply {
            lvProfessions.addHeaderView(this, "Professions")
            lvFriends.addHeaderView(this, "Friends")
        }
    }

    override fun onResume() {
        super.onResume()
        // add back arrow to toolbar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        // load invoking arguments
        val argument = intent.getParcelableExtra<Inhabitant>("inhabitant")
        argument?.let { presenter.onViewCreated(it) }

        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(detailNavigator)
    }

    override fun getToolbarInstance(): Toolbar? = toolbar

    override fun getMenuResource(): Int? = null

    override fun showInhabitantData(inhabitant: Inhabitant) {
        Picasso.with(this).load(inhabitant.image).into(ivSnapshot)
        tvName.text = inhabitant.name
        tvAge.text = getString(R.string.tv_age_text, inhabitant.age)
        tvWeight.text = getString(R.string.tv_weight_text, String.format("%.2f", inhabitant.weight))
        tvHeight.text = getString(R.string.tv_height_text, String.format("%.2f", inhabitant.height))
        tvHaircolor.text = getString(R.string.tv_haircolor_text, inhabitant.hairColor)
        lvProfessions.adapter =
                ArrayAdapter<String>(this, R.layout.listview_item, inhabitant.professions)
        lvFriends.adapter =
                ArrayAdapter<String>(this, R.layout.listview_item, inhabitant.friends)
    }

    override fun showInfoMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                presenter.backButtonClicked()
                true
            }
            else -> false
        }
    }
}

private fun ListView.addHeaderView(layoutInflater: LayoutInflater, title: String) {
    val header: View = layoutInflater.inflate(R.layout.listview_header, this, false)
    header.tv_header_listview_header.text = header.context.getString(R.string.tv_header_text, title)
    this.addHeaderView(header, null, false)
}
