package com.yaky.betaseriefollowing.data.db

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.yaky.betaseriefollowing.data.Show
import com.yaky.betaseriefollowing.data.Shows
import com.yaky.betaseriefollowing.ui.App


//TODO App.instance should be passed as parameter to be mocked for test
object  DatabaseHelper: OrmLiteSqliteOpenHelper(App.instance, "nameDb", null, 1 ) {


    override fun onCreate(db: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTable(connectionSource, Shows::class.java)
        TableUtils.createTable(connectionSource, Serie::class.java)
        TableUtils.createTable(connectionSource, Episode::class.java)
        TableUtils.createTable(connectionSource, UserSerieInfo::class.java)
        TableUtils.createTable(connectionSource, Show::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Shows, Any?>(connectionSource, Shows::class.java, true)
        TableUtils.dropTable<Serie, Any?>(connectionSource, Serie::class.java, true)
        TableUtils.dropTable<Episode, Any?>(connectionSource, Episode::class.java, true)
        TableUtils.dropTable<UserSerieInfo, Any?>(connectionSource, UserSerieInfo::class.java, true)
        TableUtils.dropTable<Show, Any?>(connectionSource, Show::class.java, true)
        onCreate(database, connectionSource)
    }
}

class ShowsDao {
    companion object {
        lateinit var dao: Dao<Shows, Int>
    }

    init {
        dao = DatabaseHelper.getDao(Shows::class.java)
    }
    fun add(show: Shows) = dao.createOrUpdate(show)

    fun queryForAll() = dao.queryForAll()
}