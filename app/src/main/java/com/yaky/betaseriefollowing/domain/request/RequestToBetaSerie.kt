package com.yaky.betaseriefollowing.domain.request

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.domain.Command
import com.yaky.betaseriefollowing.ui.App
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import java.io.IOException

//TODO use retrofit for multiple use ?
class RequestToBetaSerie : Command<Shows>, AnkoLogger {
    private val client = OkHttpClient()

    companion object {
        private val urlListSerie = App.instance.getString(R.string.betaSerieURL)
        private val image = "https://api.betaseries.com/episodes/list?v=3.0"
    }



    override fun requestListSerie() : Shows{
        //TODO parameter shouldn't be hard coded
        info{"requestListSerie request"}
        val request = Request.Builder().url(urlListSerie)
                .addHeader("X-BetaSeries-Key",  App.instance.getString(R.string.betaKey))
                .addHeader("Authorization","Bearer 2a2ca041c5a0" )
                .addHeader("Accept"," application/json")
                .build()
            val listShows: Shows? = Gson().fromJson(sendRequest(request), Shows::class.java)
            if(listShows == null){
                warn("deserialization problem ")
               throw JsonSyntaxException("Problem deserialize JSon from server")
            }else {
                return listShows
            }
    }

    private fun sendRequest(request: okhttp3.Request): String?{
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            warn("problem connection server")
            throw IOException("Unexpected code " + response)
        }
        info("response OK from server ")
        return response.body()?.string()
    }
}