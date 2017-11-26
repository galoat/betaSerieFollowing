package com.yaky.betaseriefollowing.request

import okhttp3.*
import okhttp3.Request
import java.io.IOException

//TODO use retrofit for multiple use ?
class Request(val url: String){
    val client = OkHttpClient()

    fun run() {
        //TODO parameter shouldn't be hard coded
        val request = Request.Builder().url(url)
                .addHeader("X-BetaSeries-Key","76e51c0d8c9c" )
                .addHeader("Authorization","Bearer c25f78a3e191" )
                .addHeader("Accept"," application/json")
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
        })
    }

}