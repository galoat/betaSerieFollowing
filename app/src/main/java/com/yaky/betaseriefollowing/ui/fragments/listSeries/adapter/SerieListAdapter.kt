package com.yaky.betaseriefollowing.ui.fragments.listSeries.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Serie
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.ui.fragments.listSeries.OnEpisodeSelected

import kotlinx.android.synthetic.main.item_serie.view.*

class SerieListAdapter(val items: Shows,val listener: OnEpisodeSelected) : RecyclerView.Adapter<SerieListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.bindSerie(items.listShow[position])
    }

    override fun getItemCount(): Int = items.size()

    class ViewHolder(val view: View, val listener : OnEpisodeSelected): RecyclerView.ViewHolder(view){
      //  private val iconView : ImageView = view.find(R.id.icon)
    //    private val dateView : TextView = view.find(R.id.date)


        fun bindSerie(serie: Serie){
            with(serie){
               // dateView.text = first().date.toString()
                itemView.titleSerie.text = title
                itemView.titleEpisode.text = first().title
                itemView.saison.text = first().season.toString()
                itemView.saison.text = first().episode.toString()
                view.setOnClickListener{listener.onEpisodeSelected(first())}
            }

        }
    }

}