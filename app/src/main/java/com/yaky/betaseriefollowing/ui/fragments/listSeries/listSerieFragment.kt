package com.yaky.betaseriefollowing.ui.fragments.listSeries

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Episode
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.data.classes.User
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.App
import com.yaky.betaseriefollowing.ui.fragments.listSeries.adapter.SerieListAdapter
import org.jetbrains.anko.*
import org.json.JSONException
import java.io.IOException

class ListSerieFragment : Fragment(), AnkoLogger {

    private lateinit var listener: OnEpisodeSelected
    private lateinit var user: User
    companion object {
        fun newInstance(): ListSerieFragment {
            return ListSerieFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnEpisodeSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement  OnEpisodeSelected")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val view: View = inflater?.inflate(R.layout.list_serie_fragment, container, false) as RecyclerView
        val activity = activity
        val listSeries = view.findViewById(R.id.listSeries_recycleView) as RecyclerView
        listSeries.layoutManager = LinearLayoutManager(activity)
        doAsync {
            var result : Shows? = null
            try {
                if( arguments.containsKey("user")) {
                    user = arguments.getParcelable("user")
                    info{"user token use "+ user.token}
                    result = RequestToBetaSerie().requestListSerie(user.token)
                }else{
                    info{"No key user given to fragment"}
                }
                App.daoSession.showsDao.deleteAll()
                App.daoSession.showsDao.save(result)
                val test = App.daoSession.showsDao.loadAll()
                info{test}
            }
            catch (e:Exception){
                when(e){
                    is JSONException -> {
                        uiThread {
                            context.toast(App.instance.getString(R.string.ParsingException))
                        }
                    }
                    is IOException -> {
                        uiThread {
                            context.toast(App.instance.getString(R.string.ConnectionException))
                        }
                    }
                }
                //TODO : use user ID to get the correct Shows
                val test = App.daoSession.showsDao.loadAll()
                if(test != null){
                    result = test.get(0)
                    warn{result}
                }

            }
            finally{
                uiThread {
                    if(result?.listShow != null) {
                        listSeries.adapter = SerieListAdapter(result!!, listener)
                    }else{
                        warn { "result is null " }
                    }
                }
            }
        }
        return view
    }
}

interface OnEpisodeSelected {
    fun onEpisodeSelected(episode: Episode)
}