package com.yaky.betaseriefollowing.ui.fragments.listSeries.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Serie
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.App
import com.yaky.betaseriefollowing.ui.fragments.listSeries.OnEpisodeSelected
import kotlinx.android.synthetic.main.item_serie.view.*
import org.jetbrains.anko.*


///TODO handle if a show is null
class SerieListAdapter(private val items: Shows, private val listener: OnEpisodeSelected, private val token: String) : RecyclerView.Adapter<SerieListAdapter.ViewHolder>(), AnkoLogger {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.bindSerie(items.listShow[position], token)
    }

    override fun getItemCount(): Int = items.size()
     fun clear(position: Int) {
         items.listShow[position].episode.removeAt(0)
         notifyItemRemoved(position)
    }



    inner class ViewHolder(private val view: View, private val listener : OnEpisodeSelected): RecyclerView.ViewHolder(view), AnkoLogger{

        fun bindSerie(serie: Serie, token: String){
            view.setOnLongClickListener {
                info{"on Long Click on item ${serie.title}"}
                episodeSeen(token, serie.first().id.toString(), { result: Boolean ->
                    run {
                        if (result) {
                            App.daoSession.serieDao.deleteByKey(serie.first().id.toLong())
                            clear(position)
                        }else{
                            ///TODO: SAVE THE DATA TO BE SEND WHEN THE SERVER WILL BE UP
                            App.instance.toast(App.instance.getString(R.string.SerieListCouldNotReacheServer))
                            info{"couldn't send watched to server"}
                        }
                    }
                })
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
        private fun episodeSeen(token: String, id: String,  callback: (m: Boolean) -> Unit){
            doAsync {
                val result = RequestToBetaSerie().postEpisodeSeen(token, id)
                uiThread {callback(result)}
            }
        }
    }

}