package com.clt.dumas.clem.news.dataSources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clt.dumas.clem.news.dataSources.dao.ArticleDao
import com.clt.dumas.clem.news.entities.Article

@Database(entities = [Article::class], version = 5, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao():ArticleDao

    companion object {
       var database: NewsDatabase? = null

        fun initNewsDatabase(context: Context) {
            if (database == null){
                synchronized(NewsDatabase::class){
                    database = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java, "myDB")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }

        fun getNewsDatabase():NewsDatabase?{
            return database
        }

        fun destroyDataBase(){
            database = null
        }
    }
}