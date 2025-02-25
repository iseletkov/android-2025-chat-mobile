package ru.psu.mobile.mychat.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import ru.psu.mobile.mychat.model.CPhoto

@Dao
interface CDAOPhotos {

    @Insert(onConflict = REPLACE)
    fun insert(photo : CPhoto)

}