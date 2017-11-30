package com.yaky.betaseriefollowing.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yaky.betaseriefollowing.ui.App
import org.jetbrains.anko.db.*

class SerieDbHelper(ctx : Context = App.instance): ManagedSQLiteOpenHelper(App.instance, SerieDbHelper.DB_NAME, null, SerieDbHelper.DB_VERSION){
    companion object {
        val DB_NAME ="serie.db"
        val DB_VERSION = 1
        val instance by lazy { SerieDbHelper }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Serie.NAME, true,
                Serie.ID to INTEGER + PRIMARY_KEY,
                Serie.ID_TVDB to INTEGER,
                Serie.TITLE to TEXT,
                Serie.EPISODES to INTEGER
                )
        db.createTable(Episode.NAME, true,
                Episode.ID to INTEGER + PRIMARY_KEY,
                Episode.TITLE to TEXT,
                Episode.SEASON to INTEGER,
                Episode.EPISODE to INTEGER,
                Episode.DESCRIPTION to TEXT,
                Episode.RESOURCEURL to TEXT)
        db.createTable(UserSerieInfo.NAME, true,
                UserSerieInfo.ID to INTEGER + PRIMARY_KEY  + AUTOINCREMENT,
                UserSerieInfo.SEEN to INTEGER,
                UserSerieInfo.DOWLOADED to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Serie.NAME, true)
        db.dropTable(Episode.NAME, true)
        db.dropTable(UserSerieInfo.NAME, true)
        onCreate(db)
    }
}