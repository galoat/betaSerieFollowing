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
// TODO inheritance forthe paramegit push --set-upstream origin banana_peeler;ter from database sere
// cause of ormlite the property shoulb be var ans nullable
@DatabaseTable(tableName = "shows")
data class Shows(
        @DatabaseField(generatedId=true)
        var id : Int? = null,
        @SerializedName("shows")
        @ForeignCollectionField(eager = true, columnName = "series")
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
    override fun toString(): String {

        return "Shows(id=$id, listShow="+listShow!!.take(0)
    }

}

@DatabaseTable(tableName = "series")
data class Serie(
        @DatabaseField(generatedId=false, id = true)
        var id : Long? = null,
        @DatabaseField(foreign = true,   foreignAutoCreate = true)
        var shows : Shows? = null,
        @DatabaseField(columnName = "idTheTvDb", canBeNull=true)
        var thetvdb_id : Long? = null,
        @DatabaseField(columnName = "idimDb", canBeNull=true)
        var imdb_id : String = "",
        @DatabaseField(columnName = "title", canBeNull=true)
        var title: String = "",
        @DatabaseField(columnName = "remaining", canBeNull=true)
        var remaining : Int? = null,
        @SerializedName("unseen")
        @ForeignCollectionField(eager = true)
        var episode : Collection<Episode>? = null)
    : AnkoLogger {
    operator fun get(position: Int) : Episode {
        if (episode is List && episode != null) {
                return (episode as List<Episode>) [position]
        }
        else{
            throw  ClassCastException()
        }
    }

    fun first(): Episode {
        if(episode!!.isNotEmpty()){
            /// TODO better exception
            info{"Empty episode..."}
            //return null;
        }
        return return (episode as List<Episode>)[0]

    }
}

@DatabaseTable(tableName = "episodes")
data class Episode(
        @DatabaseField(generatedId=false, id = true)
        var id : Int? = null ,
        @DatabaseField(foreign = true)
        var serie : Serie? = null,
        @DatabaseField(columnName = "season", canBeNull=true)
        var season : Int? = null,
        @DatabaseField(columnName = "episode", canBeNull=true)
        var episode : Int? = null,
        @DatabaseField(columnName = "description", canBeNull=true)
        var description : String = "",
        @DatabaseField(columnName = "date", canBeNull=true)
        var date : Date? = null,
        @DatabaseField(foreign = true)
        var user : UserSerieInfo? = null,
        @DatabaseField(columnName = "resourceURL", canBeNull=true)
        var resource_url : String? = "",
        @DatabaseField
        var title : String = "",
        @DatabaseField(foreign = true)
        @SerializedName("show") var episodeInfo : Show? = null
)
@DatabaseTable()
data class UserSerieInfo(
        @DatabaseField(generatedId=true)
        var id : Long? = null,
        @DatabaseField(columnName = "seen", canBeNull=true)
        var seen : Boolean? = null,
        @DatabaseField(columnName = "downloaded", canBeNull=true)
        var downloaded : Boolean? = null)
@DatabaseTable()
data class Show(
        @DatabaseField(generatedId=false,  columnName = "ID", id = true)
        var id : Long? = null,
        @DatabaseField(columnName = "theTvDbID", canBeNull=true)
        var thevdb_Id : Long? = null,
        @DatabaseField(columnName = "title", canBeNull=true)
        var title : String = "")