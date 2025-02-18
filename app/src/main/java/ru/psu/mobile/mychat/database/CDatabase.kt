package ru.psu.mobile.mychat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.psu.mobile.mychat.database.dao.CDAOCheckPoints
import ru.psu.mobile.mychat.model.CCheckPoint


@Database(entities = [CCheckPoint::class], version = 1)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoCheckPoints(): CDAOCheckPoints

    companion object {
        @Volatile
        private var INSTANCE: CDatabase? = null
        fun getDatabase(context: Context): CDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context,
                            CDatabase::class.java,
                            "database.db")
                            .build()
                }

            }
            return INSTANCE!!
        }
    }
}