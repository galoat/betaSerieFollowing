package com.yaky.betaseriefollowing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yaky.betaseriefollowing.adapter.SerieListAdapter
import com.yaky.betaseriefollowing.data.Shows
import com.yaky.betaseriefollowing.request.RequestToBetaSerie
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listSeries : RecyclerView = find(R.id.listSeries)

        listSeries.layoutManager = LinearLayoutManager(this)
        doAsync {
            val result : Shows? = RequestToBetaSerie().requestListSerie()
            if(result != null) {
                info{result.size}
                uiThread {
                    listSeries.adapter = SerieListAdapter(result)
                }
            }
            else{
                //TODO endel the case when  the resykt isnull
                info{"Result null"}
            }
        }
    }
}


