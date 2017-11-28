package com.yaky.betaseriefollowing.data

import com.google.gson.annotations.SerializedName
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

/*abstract class DataFromBetaSerie(val id: Long,
                                 val thetvdb_id : Long)*/
// TODO inheritance forthe parameter from database sere

data class Shows(@SerializedName("shows") val listShow : List<Serie>){
    val size: Int
        get() = listShow.size
    operator fun get(position: Int): Serie = listShow[position]
    operator fun iterator(): Iterator<Serie> = listShow.iterator()
}

data class Serie(val id : Long,
                 val thetvdb_id : Long,
                 val imdb_id : String,
                 val title: String,
                 val remaining : Int,
                 @SerializedName("unseen") val episode : List<Episode>
                 ): AnkoLogger {
    operator fun get(position: Int) : Episode = episode[position]

    fun first(): Episode {
        if(episode.isNotEmpty()){
            /// TODO better exception
            info{"Empty episode..."}
            //return null;
        }
        return episode[0]

    }
}

data class Episode(val title: String,
                   val season : Int,
                   val episode : Int,
                   val description : String,
                   val date : Date,
                   val user : UserSerieInfo,
                   val resource_url : String?,
                   @SerializedName("show") val episodeInfo : Show
)

data class UserSerieInfo(val seen : Boolean,
                         val downloaded : Boolean)

data class Show(val id : Long,
                val thevdb_Id : Long,
                val title : String)