package com.fin.sampletest.database

import android.content.Context
import androidx.room.*
import com.fin.sampletest.data.RepoResponse
import com.fin.sampletest.database.dao.RepoDAO
import com.fin.sampletest.database.typeconverter.OwnerTypeConverter


@Database (entities = [(RepoResponse::class)],version = 3)
@TypeConverters(OwnerTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDAO


    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "repo"
                ).allowMainThreadQueries().build()

            }

            return INSTANCE
        }
        fun getDao() :RepoDAO? {
            return INSTANCE?.repoDao()
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}