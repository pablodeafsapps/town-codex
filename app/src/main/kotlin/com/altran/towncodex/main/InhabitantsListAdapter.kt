package com.altran.towncodex.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.altran.towncodex.R
import com.altran.towncodex.model.Inhabitant
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.card_view_custom_layout.view.*

class InhabitantsListAdapter(private var listener: (Inhabitant?) -> Unit, private var dataList: List<Inhabitant>?) :
        RecyclerView.Adapter<InhabitantsListAdapter.ViewHolder>() {

    // Creating card_view_custom_layout ViewHolder to speed up the performance
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ctx: Context? = itemView.context
        val tvName: TextView? = itemView.tv_name_card_view_custom_layout
        val tvAge: TextView? = itemView.tv_age_card_view_custom_layout
        val tvWeight: TextView? = itemView.tv_weight_card_view_custom_layout
        val tvHeight: TextView? = itemView.tv_height_card_view_custom_layout
        val ivSnapshot: ImageView? = itemView.iv_snapshot_card_view_custom_layout
    }

    override fun getItemCount() = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        // create a new view
        val viewRow = LayoutInflater.from(parent?.context).inflate(R.layout.card_view_custom_layout, parent, false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        dataList?.let {
            holder?.tvName?.text = it[position].name
            holder?.tvAge?.text = holder?.ctx?.getString(R.string.tv_age_text, it[position].age)
            holder?.tvWeight?.text = holder?.ctx?.getString(R.string.tv_weight_text, String.format("%.2f", it[position].weight))
            holder?.tvHeight?.text = holder?.ctx?.getString(R.string.tv_height_text, String.format("%.2f", it[position].height))
            Picasso.with(holder?.ivSnapshot?.context)
                    .load(it[position].image).into(holder?.ivSnapshot)
            holder?.itemView?.setOnClickListener { listener(dataList?.get(position)) }
        }
    }

    fun updateData(list: List<Inhabitant>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
