package com.yaky.betaseriefollowing.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.Shows
import com.yaky.betaseriefollowing.domain.request.RequestToBetaSerie
import com.yaky.betaseriefollowing.ui.adapter.SerieListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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


