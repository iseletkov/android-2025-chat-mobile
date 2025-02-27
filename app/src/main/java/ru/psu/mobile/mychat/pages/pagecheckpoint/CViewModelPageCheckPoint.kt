package ru.psu.mobile.mychat.pages.pagecheckpoint

import android.app.Application
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.psu.mobile.mychat.R
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.model.CCheckPointWithRelations
import ru.psu.mobile.mychat.model.CPhoto
import ru.psu.mobile.mychat.repositories.CRepositoryCheckPoints
import java.util.UUID


class CViewModelPageCheckPoint(
    private val context: Application
) : AndroidViewModel(context)
{
    private val repositoryCheckPoints = CRepositoryCheckPoints(context)

    private val checkpointIdFlow = MutableStateFlow<UUID?>(null)

    fun setId(
        id : UUID
    ) {
        checkpointIdFlow.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    var checkpoint: StateFlow<CCheckPointWithRelations> = checkpointIdFlow.flatMapLatest { checkpointId ->
        if (checkpointId==null)
            emptyFlow()
        else
            repositoryCheckPoints.getByIdWithRelations(checkpointId)
    }
        .stateIn(viewModelScope, SharingStarted.Lazily,
            CCheckPointWithRelations(
                CCheckPoint(UUID.randomUUID(), "", "", ""),
                photos = emptyList()
            )
        )


    fun addPhoto()
    {
        //Если контрольная точка не установлена, фото не сохраняем.
        checkpointIdFlow.value?:return

        //TODO Временное решение. Вместо вызова фотоприложения берём статическую фотографию из ресурсов.
        val bm = BitmapFactory.decodeResource(context.resources, R.mipmap.dog1)

        viewModelScope.launch(Dispatchers.IO) {
            repositoryCheckPoints.savePhoto(
                CPhoto(
                    UUID.randomUUID(),
                    "dog.jpg",
                    checkpointIdFlow.value!!,
                    bm
                )
            )
        }
    }
}