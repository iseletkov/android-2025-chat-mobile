package ru.psu.mobile.mychat.pages.pageroute

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.psu.mobile.mychat.model.CCheckPoint
import ru.psu.mobile.mychat.repositories.CRepositoryCheckPoints

class CViewModelPageRoute(application: Application) :  AndroidViewModel(application)
{
    private val repositoryCheckPoints = CRepositoryCheckPoints(application)

    val checkpoints: StateFlow<List<CCheckPoint>> = repositoryCheckPoints.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Это кусок кода для вставки тестовых элементов в БД.
//    init {
//        kotlinx.coroutines.MainScope().launch(Dispatchers.IO) {
//
//
//            repositoryCheckPoints.insert(
//                CCheckPoint(
//                    UUID.randomUUID(),
//                    "Контрольная точка 1",
//                    "флвьфджлв",
//                    "adadadadadawd"
//                )
//            )
//
//            repositoryCheckPoints.insert(
//                CCheckPoint(
//                    UUID.randomUUID(),
//                    "Контрольная точка 2",
//                    "adawda",
//                    "adadadad adawd a;ld ;almf;aknwf; adawd"
//                )
//            )
//        }
//    }


//    private val _checkpoints = mutableStateListOf<CCheckPoint>(
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 1",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 2",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 3",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 4",
//            "флвьфджлв",
//            "adadadadadawd"
//        ), CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 5",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 6",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 7",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 8",
//            "флвьфджлв",
//            "adadadadadawd"
//        ),
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 9",
//            "флвьфджлв",
//            "adadadadadawd"
//        )
//        ,
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 10",
//            "флвьфджлв",
//            "adadadadadawd"
//        )
//        ,
//        CCheckPoint(
//            UUID.randomUUID(),
//            "Контрольная точка 11",
//            "флвьфджлв",
//            "adadadadadawd"
//        )
//    )

//    val checkpoints: List<CCheckPoint>
//        get() = _checkpoints

    fun addCheckPoint(
        checkpoint : CCheckPoint
    )
    {
        //Здесь надо проверить корректность данных.


        //Здесь запрос в БД

        kotlinx.coroutines.MainScope().launch(Dispatchers.IO) {
            repositoryCheckPoints.insert(checkpoint)
        }
        //_checkpoints.add(checkPoint)
    }

    fun remove(
        checkpoint : CCheckPoint
    )
    {
        //Здесь запрос в БД
        MainScope().launch(Dispatchers.IO) {
            repositoryCheckPoints.delete(checkpoint)
        }

        //_checkpoints.remove(checkpoint)
    }

}