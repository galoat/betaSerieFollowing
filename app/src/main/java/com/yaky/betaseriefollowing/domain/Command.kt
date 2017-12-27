package com.yaky.betaseriefollowing.domain

import com.yaky.betaseriefollowing.data.classes.User

interface Command<out T>{
    fun requestListSerie(): T?
     fun requestCredential(user: User): String
}

