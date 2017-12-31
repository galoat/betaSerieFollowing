package com.yaky.betaseriefollowing.domain

import com.yaky.betaseriefollowing.data.classes.Episode
import com.yaky.betaseriefollowing.data.classes.User

interface Command<out T>{
    fun requestListSerie(token: String): T?
    fun postEpisodeSeen(token: String, episode: Episode):Boolean
    fun requestCredential(user: User): String
}

