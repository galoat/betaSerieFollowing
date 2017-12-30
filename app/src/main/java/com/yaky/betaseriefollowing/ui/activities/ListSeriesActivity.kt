package com.yaky.betaseriefollowing.ui.activities

import android.content.res.Configuration
import android.os.Bundle
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Episode
import com.yaky.betaseriefollowing.data.classes.User
import com.yaky.betaseriefollowing.ui.fragments.MoreSerieFragment
import com.yaky.betaseriefollowing.ui.fragments.listSeries.ListSerieFragment
import com.yaky.betaseriefollowing.ui.fragments.listSeries.OnEpisodeSelected
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ListSeriesActivity : BaseActivity(), AnkoLogger, OnEpisodeSelected {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user : User? = null
        if(intent.hasExtra("user")){
            user = intent.getParcelableExtra("user")
        }else{
            info{"No key user send"}
        }
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {

            val fragment = ListSerieFragment.newInstance()
            if(user != null){
                val bundle = Bundle()
                bundle.putParcelable("user", user)
                fragment.arguments = bundle
            }
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listFragment, fragment, "listSeries")
                    .commit()
        }
    }
}


