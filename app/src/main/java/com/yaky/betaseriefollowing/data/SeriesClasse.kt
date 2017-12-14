package com.yaky.betaseriefollowing.data

import com.google.gson.annotations.SerializedName
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

/*abstract class DataFromBetaSerie(val id: Long,
                                 val thetvdb_id : Long)*/
// TODO inheritance forthe parameter from database sere
// cause of ormlite the property shoulb be var ans nullable
@DatabaseTable(tableName = "listShows")
data class Shows(
        @DatabaseField(generatedId=true)
        var id : Int? = null,
        @SerializedName("shows")
        @ForeignCollectionField(eager = true)
        var listShow : Collection<Serie>? = null){
    val size: Int
        get() = listShow!!.size

    operator fun get(position: Int): Serie {
        if(listShow is List){
            return (listShow as List<Serie>)[position]
        }
        else{
            throw  ClassCastException()
        }
    }

    operator fun iterator(): Iterator<Serie> = listShow!!.iterator()

}

@DatabaseTable(tableName = "series")
data class Serie(
        @DatabaseField(generatedId=false)
        val id : Long,
        @DatabaseField(columnName = "idTheTvDb", canBeNull=true)
        val thetvdb_id : Long,
        @DatabaseField(columnName = "idimDb", canBeNull=true)
        val imdb_id : String,
        @DatabaseField(columnName = "title", canBeNull=true)
        val title: String,
        @DatabaseField(columnName = "remaining", canBeNull=true)
        val remaining : Int,
        @SerializedName("unseen")
        @ForeignCollectionField(eager = true)
        val episode : List<Episode>)
    : AnkoLogger {
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

@DatabaseTable(tableName = "episodes")
data class Episode(
        @DatabaseField(generatedId=false)
        val id : Int ,
        @DatabaseField(columnName = "season", canBeNull=true)
        val season : Int,
        @DatabaseField(columnName = "episode", canBeNull=true)
        val episode : Int,
        @DatabaseField(columnName = "description", canBeNull=true)
        val description : String,
        @DatabaseField(columnName = "date", canBeNull=true)
        val date : Date,
        @DatabaseField(foreign = true)
        val user : UserSerieInfo,
        @DatabaseField(columnName = "resourceURL", canBeNull=true)
        val resource_url : String?,
        @DatabaseField
        val title : String,
        @DatabaseField(foreign = true)
        @SerializedName("show") val episodeInfo : Show
)
@DatabaseTable()
data class UserSerieInfo(
        @DatabaseField(generatedId=true)
        val id : Long,
        @DatabaseField(columnName = "seen", canBeNull=true)
        val seen : Boolean,
        @DatabaseField(columnName = "seen", canBeNull=true)
        val downloaded : Boolean)
@DatabaseTable()
data class Show(
        @DatabaseField(generatedId=false)
        val id : Long,
        @DatabaseField(columnName = "theTvDbID", canBeNull=true)
        val thevdb_Id : Long,
        @DatabaseField(columnName = "title", canBeNull=true)
        val title : String)