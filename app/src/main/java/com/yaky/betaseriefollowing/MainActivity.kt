package com.yaky.betaseriefollowing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yaky.betaseriefollowing.adapter.SerieListAdapter
import com.yaky.betaseriefollowing.request.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val items = listOf(
    "Serie1 - Sunny - 31/17",
    "Tue 6/24 - Foggy - 21/8",
    "Wed 6/25 - Cloudy - 22/17",
    "Thurs 6/26 - Rainy - 18/11",
    "Fri 6/27 - Foggy - 21/10",
    "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
    "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listSeries : RecyclerView = find(R.id.listSeries)
        val url = "https://api.betaseries.com/episodes/list?v=3.0"
        listSeries.layoutManager = LinearLayoutManager(this)
        listSeries.adapter = SerieListAdapter(items)
        doAsync {
            Request(url).run()
            uiThread { longToast("test") }

        }
    }
}


