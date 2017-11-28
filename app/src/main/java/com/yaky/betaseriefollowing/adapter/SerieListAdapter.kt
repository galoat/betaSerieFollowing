package com.yaky.betaseriefollowing.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.Serie
import com.yaky.betaseriefollowing.data.Shows
import org.jetbrains.anko.find

class SerieListAdapter(val items: Shows) : RecyclerView.Adapter<SerieListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.bindSerie(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val iconView : ImageView = view.find(R.id.icon)
        private val dateView : TextView = view.find(R.id.date)
        private val titleSerie : TextView = view.find(R.id.titleSerie)
        private val titleEpisode : TextView = view.find(R.id.titleEpisode)
        private val seasonView : TextView = view.find(R.id.saison)
        private val episodeView : TextView = view.find(R.id.episode)

        fun bindSerie(serie: Serie){
            with(serie){
               // dateView.text = first().date.toString()
                titleSerie.text = title
                titleEpisode.text = first().title
                seasonView.text = first().season.toString()
                episodeView.text = first().episode.toString()

            }
        }
    }

}