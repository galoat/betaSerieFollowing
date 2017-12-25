package com.yaky.betaseriefollowing.domain

 interface Command<out T>{
    fun requestListSerie(): T?
}

