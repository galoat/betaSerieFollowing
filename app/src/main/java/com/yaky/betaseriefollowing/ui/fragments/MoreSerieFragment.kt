package com.yaky.betaseriefollowing.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Episode
import org.jetbrains.anko.AnkoLogger

class MoreSerieFragment : Fragment(), AnkoLogger {

    companion object {

        private const val EPISODE = "episode"

        fun newInstance(episode: Episode): MoreSerieFragment {
            val args = Bundle()
            args.putParcelable(EPISODE, episode as Parcelable)
            val fragment = MoreSerieFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater?.inflate(R.layout.more_serie_fragment, container, false) as View
        val episode : Episode = getArguments().getParcelable(EPISODE)
        val titleEpisode = view.findViewById(R.id.titleEpisode) as TextView
        titleEpisode.text = episode.title
        return view
    }
}
