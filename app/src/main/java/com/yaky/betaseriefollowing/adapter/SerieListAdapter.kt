package com.yaky.betaseriefollowing.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.yaky.betaseriefollowing.data.Shows

class SerieListAdapter(val items: Shows) : RecyclerView.Adapter<SerieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO : Better display
        with(items[position]) {
            holder.textView.text = title
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

}