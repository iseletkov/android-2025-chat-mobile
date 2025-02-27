package ru.psu.mobile.mychat.pages.pageroute

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.model.CCheckPointWithRelations
import ru.psu.mobile.mychat.model.CPhoto
import ru.psu.mobile.mychat.repositories.CRepositoryCheckPoints
import java.util.UUID

class CViewModelPageRoute(application: Application) :  AndroidViewModel(application)
{
    private val repositoryCheckPoints = CRepositoryCheckPoints(application)

    val checkpoints: StateFlow<List<CCheckPointWithRelations>> = repositoryCheckPoints.getAllWithRelations()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Это кусок кода для вставки тестовых элементов в БД.
//    init {
//        MainScope().launch(Dispatchers.IO) {
//
//
//            repositoryCheckPoints.insert(
//                CCheckPoint(
//                    UUID.fromString("6d37dad0-6bb3-4548-80c1-e8e7be9f7a82"),
//                    "Контрольная точка 1",
//                    "флвьфджлв",
//                    "adadadadadawd"
//                )
//            )
//
//
//
//            repositoryCheckPoints.savePhoto(
//                CPhoto(
//                    UUID.fromString("6d37dad0-6bb2-4548-80c1-e8e7be9f7a82"),
//                    "dog.png",
//                    UUID.fromString("6d37dad0-6bb3-4548-80c1-e8e7be9f7a82"),
//                    null
//                )
//            )
//
//
//        }
//    }


    fun addCheckPoint(
        checkpoint : CCheckPoint
    )
    {
        //Здесь надо проверить корректность данных.


        //Здесь запрос в БД
        MainScope().launch(Dispatchers.IO) {
            repositoryCheckPoints.insert(checkpoint)
        }
    }

    fun remove(
        checkpoint : CCheckPoint
    )
    {
        //Здесь запрос в БД
        MainScope().launch(Dispatchers.IO) {
            repositoryCheckPoints.delete(checkpoint)
        }
    }

}