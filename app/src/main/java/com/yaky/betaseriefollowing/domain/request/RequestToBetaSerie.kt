package com.yaky.betaseriefollowing.domain.request

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.yaky.betaseriefollowing.Exception.CredentialException
import com.yaky.betaseriefollowing.R
import com.yaky.betaseriefollowing.data.classes.Shows
import com.yaky.betaseriefollowing.data.classes.User
import com.yaky.betaseriefollowing.domain.Command
import com.yaky.betaseriefollowing.ui.App
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import java.io.IOException
import java.util.concurrent.TimeUnit


//TODO use retrofit for multiple use ?
class RequestToBetaSerie : Command<Shows>, AnkoLogger {


    private val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    companion object {
        private val urlListSerie = App.instance.getString(R.string.betaSerieURL)
    }


    override fun requestListSerie(token : String) : Shows{
        //TODO parameter shouldn't be hard coded
        info{"requestListSerie request"}
        val request = Request.Builder()
                .addHeader("Authorization", token)
                .addHeader("Accept"," application/json")
            val listShows: Shows? = Gson().fromJson(sendRequest(request, App.instance.getString(R.string.betaSerieURLEpisodeList)), Shows::class.java)
            if(listShows == null){
                warn("deserialization problem ")
               throw JsonSyntaxException("Problem deserialize JSon from server")
            }else {
                return listShows
            }
    }

    ///TODO use Oauth2 and not HTTPS
    override fun requestCredential(user: User): String {
        info{"request credential user "+user.login +" login "+user.password}
        val formBody = FormBody.Builder()
                .add("login", user.login)
                .add("password", user.password)
                .build()
        val request = Request.Builder().post(formBody)
        val credential =sendRequest(request, App.instance.getString(R.string.betaSerieURLHttpsLogin))
        return credential ?: throw CredentialException("Can't identificate User")
    }


    private fun sendRequest(requestBuilder: okhttp3.Request.Builder, requestUrl: String): String?{
        val request = requestBuilder.addHeader("X-BetaSeries-Key",  App.instance.getString(R.string.betaKey) )
                .url(urlListSerie + requestUrl)
                .build()
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            warn("problem connection server \n error :"+  response.body()?.string())
            throw IOException("Unexpected code " + response)
        }

        info("response OK from server ")
        return response.body()?.string()
    }
}