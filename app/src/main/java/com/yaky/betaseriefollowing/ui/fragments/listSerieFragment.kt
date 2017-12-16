package com.yaky.betaseriefollowing.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.App
import com.yaky.betaseriefollowing.ui.adapter.SerieListAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread


class ListSerieFragment : Fragment(), AnkoLogger {

    companion object {

        fun newInstance(): ListSerieFragment {
            return ListSerieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater?.inflate(R.layout.list_serie_fragment, container, false) as RecyclerView
        val activity = activity
        val listSeries = view.findViewById(R.id.listSeries_recycleView) as RecyclerView
        listSeries.layoutManager = LinearLayoutManager(activity)
        doAsync {
            val result : Shows? = RequestToBetaSerie().requestListSerie()
            if(result != null) {
                //info{result.listShow[0]}
                uiThread {
                    listSeries.adapter = SerieListAdapter(result)
                }
                App.daoSession.showsDao.save(result)
                val test = App.daoSession.showsDao.loadAll()
                info{test}
            }
            else{
                //TODO endel the case when  the resykt isnull
                info{"Result null"}
            }
        }
        return view
    }

}
