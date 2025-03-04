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
import ru.psu.mobile.mychat.repositories.CRepositoryCheckPoints

class CViewModelPageRoute(application: Application) :  AndroidViewModel(application)
{
    private val repositoryCheckPoints = CRepositoryCheckPoints(application)

    val checkpoints: StateFlow<List<CCheckPointWithRelations>> = repositoryCheckPoints.getAllWithRelations()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Это кусок кода для вставки тестовых элементов в БД.
    init {
        viewModelScope.launch(Dispatchers.IO) {

            //Запрос на обновление данных с сервера.
            repositoryCheckPoints.updateCheckPointsFromServer()
        }
    }


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