package com.altran.towncodex.main

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.altran.towncodex.R
import com.altran.towncodex.model.Inhabitant

import kotlinx.android.synthetic.main.card_view_custom_layout.view.*

class InhabitantsListAdapter(private var listener: (Inhabitant?) -> Unit, private var dataList: List<Inhabitant>?) :
        RecyclerView.Adapter<InhabitantsListAdapter.ViewHolder>() {

    // Creating card_view_custom_layout ViewHolder to speed up the performance
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView? = itemView.tv_id_card_view_custom_layout
        val tvName: TextView? = itemView.tv_name_card_view_custom_layout
    }

    override fun getItemCount() = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        // create a new view
        val viewRow = LayoutInflater.from(parent?.context).inflate(R.layout.card_view_custom_layout, parent, false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvId?.text = dataList?.let { it[position].id.toString() }
        holder?.tvName?.text = dataList?.let { Html.fromHtml(it[position].name) }
        holder?.itemView?.setOnClickListener { listener(dataList?.get(position)) }
    }

    fun updateData(list: List<Inhabitant>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
