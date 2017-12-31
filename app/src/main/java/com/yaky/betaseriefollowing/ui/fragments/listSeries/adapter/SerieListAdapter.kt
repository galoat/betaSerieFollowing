package com.yaky.betaseriefollowing.ui.fragments.listSeries.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Serie
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.fragments.listSeries.OnEpisodeSelected
import kotlinx.android.synthetic.main.item_serie.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info


///TODO handle if a show is null
class SerieListAdapter(private val items: Shows, private val listener: OnEpisodeSelected, private val token: String) : RecyclerView.Adapter<SerieListAdapter.ViewHolder>(), AnkoLogger {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.bindSerie(items.listShow[position], token)
    }

    override fun getItemCount(): Int = items.size()

    class ViewHolder(private val view: View, private val listener : OnEpisodeSelected): RecyclerView.ViewHolder(view), AnkoLogger{


        fun bindSerie(serie: Serie, token: String){
            view.setOnLongClickListener {
                info{"on Long Click on item ${serie.title}"}
                doAsync {
                    RequestToBetaSerie().postEpisodeSeen(token, serie.first())
                }
                true
            }
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