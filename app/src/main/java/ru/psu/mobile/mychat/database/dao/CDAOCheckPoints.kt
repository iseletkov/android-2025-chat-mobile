package ru.psu.mobile.mychat.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.model.CCheckPointWithRelations
import java.util.UUID

@Dao
interface CDAOCheckPoints {
    @Query("SELECT * FROM checkpoints")
    fun getAll(): Flow<List<CCheckPoint>>

    @Transaction
    @Query("SELECT * FROM checkpoints")
    fun getCheckpointsWithPhotos(): Flow<List<CCheckPointWithRelations>>

    @Query("SELECT * FROM checkpoints WHERE id=:id")
    fun getById(id: UUID): Flow<CCheckPoint?>

    @Transaction
    @Query("SELECT * FROM checkpoints WHERE id=:id")
    fun getByIdWithRelation(id: UUID): Flow<CCheckPointWithRelations>

    @Insert(onConflict = REPLACE)
    fun insert(checkPoint : CCheckPoint)

    @Insert(onConflict = REPLACE)
    fun insertAll(checkpoints: List<CCheckPoint>)

    @Update
    suspend fun update(checkPoint : CCheckPoint)
    @Delete
    suspend fun delete(checkPoint : CCheckPoint)
}