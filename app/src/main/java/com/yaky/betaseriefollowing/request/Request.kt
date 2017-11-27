package com.yaky.betaseriefollowing.request


import com.google.gson.Gson
import com.yaky.betaseriefollowing.data.Shows
import com.yaky.betaseriefollowing.domain.Command
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.IOException

//TODO use retrofit for multiple use ?
class Request : Command<Shows>, AnkoLogger {
    private val client = OkHttpClient()

    companion object {
        private val url = "https://api.betaseries.com/episodes/list?v=3.0"
    }


    override fun execute() : Shows?{
        //TODO parameter shouldn't be hard coded
        info{"execute request"}
        val request = Request.Builder().url(url)
                .addHeader("X-BetaSeries-Key","76e51c0d8c9c" )
                .addHeader("Authorization","Bearer c25f78a3e191" )
                .addHeader("Accept"," application/json")
                .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful()) {
            throw IOException("Unexpected code " + response)
        }
        info("response OK from server ")
        val result = response.body()?.string();
        val test : Shows?
        test = Gson().fromJson(result, Shows::class.java)
        info{test.listShow.first()}
        return test
    }
}