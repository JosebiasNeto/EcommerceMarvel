package com.example.ecommercemarvel.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommercemarvel.domain.model.ComicEntity

@Database(entities = [ComicEntity::class], version = 1)
abstract class ComicsDatabase : RoomDatabase() {

    abstract fun comicsDao(): ComicsDao

    companion object {
        @Volatile
        private var INSTANCE: ComicsDatabase? = null

        fun getDatabase(context: Context): ComicsDatabase {
            val tempInstace = INSTANCE
            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ComicsDatabase::class.java,
                    "comics_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}