package com.yaky.betaseriefollowing.ui

import android.app.Application
import com.yaky.betaseriefollowing.data.classes.DaoMaster
import com.yaky.betaseriefollowing.data.classes.DaoSession


class App( ) : Application(){

    companion object {
        var instance : App by DelegateExt.notNullSingleValue()
        lateinit var daoSession :DaoSession
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val helper = DaoMaster.DevOpenHelper(this, "notes-db")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }
}
