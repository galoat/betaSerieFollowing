package com.yaky.betaseriefollowing.ui

import android.app.Application


class App : Application(){
    companion object {
        var instance : App by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
