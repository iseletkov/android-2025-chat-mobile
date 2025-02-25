package ru.psu.mobile.mychat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.psu.mobile.mychat.database.dao.CDAOCheckPoints
import ru.psu.mobile.mychat.database.dao.CDAOPhotos
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.model.CPhoto


@Database(entities = [
    CCheckPoint::class,
    CPhoto::class], version = 1)
abstract class CDatabase : RoomDatabase() {
    abstract fun daoCheckPoints(): CDAOCheckPoints
    abstract fun daoPhotos(): CDAOPhotos
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