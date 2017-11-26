package com.yaky.betaseriefollowing.data

import java.util.*

/*abstract class DataFromBetaSerie(val id: Long,
                                 val thetvdb_id : Long)*/
// TODO inheritance forthe parameter from database sere

data class Shows(val shows : List<Show>)

data class Show (val id : Long,
                 val thetvdb_id : Long,
                 val imdb_id : String,
                 val title: String,
                 val remaining : Int,
                 val unseen : List<Episode>
                 )

data class Episode(val id : Long,
                   val thetvdb_id : Long,
                   val youtube_id : String?,
                   val season : Int,
                   val episode : Int,
                   val description : String,
                   val date : Date,
                   val user : UserSerieInfo,
                   val resource_url : String?
)

data class UserSerieInfo(val seen : Boolean,
                         val downloaded : Boolean)