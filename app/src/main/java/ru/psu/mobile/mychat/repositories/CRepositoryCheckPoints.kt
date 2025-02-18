package ru.psu.mobile.mychat.repositories

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.psu.mobile.mychat.database.CDatabase
import ru.psu.mobile.mychat.model.CCheckPoint

class CRepositoryCheckPoints(
    context : Context
)
{
    private val db = CDatabase.getDatabase(context)
    private val daoCheckPoints = db.daoCheckPoints()

    fun getAll() : Flow<List<CCheckPoint>>
    {
        //Обмен с сервером

        return daoCheckPoints.getAll()
    }

    suspend fun insert(
        checkPoint: CCheckPoint
    )
    {
        daoCheckPoints.insert(checkPoint)
    }

    suspend fun delete(
        checkPoint: CCheckPoint
    )
    {
        daoCheckPoints.delete(checkPoint)
    }


}