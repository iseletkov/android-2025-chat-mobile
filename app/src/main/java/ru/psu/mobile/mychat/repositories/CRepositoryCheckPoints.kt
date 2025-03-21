package ru.psu.mobile.mychat.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEach
import ru.psu.mobile.mychat.database.CDatabase
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.model.CCheckPointWithRelations
import ru.psu.mobile.mychat.model.CPhoto
import ru.psu.mobile.mychat.util.retrofit.CAPICheckPoints
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.UUID


class CRepositoryCheckPoints(
    val context                             : Context
)
{
    private val db                          = CDatabase.getDatabase(context)
    private val daoCheckPoints              = db.daoCheckPoints()
    private val daoPhotos                   = db.daoPhotos()

    private val filesDir                    = File("${context.filesDir}/images")

    fun getAll() : Flow<List<CCheckPoint>>
    {
        //Обмен с сервером

        return daoCheckPoints.getAll()
    }

    private fun readPhotos(
        checkpointWithRelations             : CCheckPointWithRelations
    )
    {
        checkpointWithRelations.photos
            .onEach { photo->

                FileInputStream(File("${filesDir}/${photo.id}.jpg")).use{inputStream->
                    photo.bitmap = BitmapFactory.decodeStream(inputStream)
                }
            }
    }

    fun getAllWithRelations() : Flow<List<CCheckPointWithRelations>>
    {
        return daoCheckPoints.getCheckpointsWithPhotos()
            .onEach { listCheckPoints ->
                listCheckPoints.onEach { checkpointWithRelations ->
                    readPhotos(checkpointWithRelations)
                }
            }
    }

    fun getByIdWithRelations(
        id : UUID
    ) : Flow<CCheckPointWithRelations>
    {
        return daoCheckPoints.getByIdWithRelation(id)
            .filterNotNull()
            .onEach { checkpointWithRelations ->
                readPhotos(checkpointWithRelations)
            }
    }

    suspend fun insert(
        checkPoint: CCheckPoint
    )
    {
        daoCheckPoints.insert(checkPoint)
    }

    suspend fun insertAll(
        checkPoints: List<CCheckPoint>
    )
    {
        daoCheckPoints.insertAll(checkPoints)
    }
    suspend fun delete(
        checkPoint: CCheckPoint
    )
    {
        //Записи о фотографиях из БД удаляться каскадом при удалении контрольной точки.
        //А сами файлы надо удалить вручную, перед удалением из БД.

        val checkpointWithRelations = getByIdWithRelations(checkPoint.id).firstOrNull()
        checkpointWithRelations ?: return

        checkpointWithRelations.photos.forEach { photo ->
            val file = File("${filesDir}/${photo.id}.jpg")
            file.delete()
        }

        daoCheckPoints.delete(checkPoint)
    }

    suspend fun savePhoto(
        photo: CPhoto
    )
    {
        photo.bitmap?.let{ bitmap ->
            FileOutputStream(File("${filesDir}/${photo.id}.jpg")).use{ outputStream->
                outputStream.write(bitmap.toByteArray())
            }

            daoPhotos.insert(photo)
        }
    }

    suspend fun updateCheckPointsFromServer()
    {
        //Запрос данных на сервере
        val listcheckpoints = CAPICheckPoints.retrofitService.getCheckPoints()

        //Сохранение данных в локальную БД.
        insertAll(listcheckpoints)
    }
}

// extension function to convert bitmap to byte array
fun Bitmap.toByteArray():ByteArray{
    ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG,10,this)
        return toByteArray()
    }
}