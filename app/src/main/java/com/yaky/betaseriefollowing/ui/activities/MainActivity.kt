package com.yaky.betaseriefollowing.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Episode
import com.yaky.betaseriefollowing.ui.fragments.MoreSerieFragment
import com.yaky.betaseriefollowing.ui.fragments.listSeries.ListSerieFragment
import com.yaky.betaseriefollowing.ui.fragments.listSeries.OnEpisodeSelected
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger, OnEpisodeSelected {
    override fun onEpisodeSelected(episode: Episode) {
        val detailsFragment = MoreSerieFragment.newInstance(episode)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.rootFragment, detailsFragment, "moreSerie")
                .addToBackStack(null)
                .commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.rootFragment, ListSerieFragment.newInstance(), "listSeries")
                    .commit()
        }
    }
}


