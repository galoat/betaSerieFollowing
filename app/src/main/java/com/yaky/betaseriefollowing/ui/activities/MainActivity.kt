package com.yaky.betaseriefollowing.ui.activities

import android.content.res.Configuration
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


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.moreEpisode, detailsFragment, "moreSerie")
                        .addToBackStack(null)
                        .commit()
        }else{
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.listFragment, detailsFragment, "moreSerie")
                    .addToBackStack(null)
                    .commit()
        }

    }


/*
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }*/


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listFragment, ListSerieFragment.newInstance(), "listSeries")
                    .commit()
        }
    }
}


