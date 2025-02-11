package ru.psu.mobile.mychat.pages.pageroute

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import ru.psu.mobile.mychat.model.CCheckPoint
import java.util.UUID

class CViewModelPageRoute :  ViewModel()
{
    private val _checkpoints = mutableStateListOf<CCheckPoint>(
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 1",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 2",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 3",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 4",
            "флвьфджлв",
            "adadadadadawd"
        ), CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 5",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 6",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 7",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 8",
            "флвьфджлв",
            "adadadadadawd"
        ),
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 9",
            "флвьфджлв",
            "adadadadadawd"
        )
        ,
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 10",
            "флвьфджлв",
            "adadadadadawd"
        )
        ,
        CCheckPoint(
            UUID.randomUUID(),
            "Контрольная точка 11",
            "флвьфджлв",
            "adadadadadawd"
        )
    )

    val checkpoints: List<CCheckPoint>
        get() = _checkpoints

    fun addCheckPoint(
        checkPoint : CCheckPoint
    )
    {
        //Здесь надо проверить корректность данных.


        //Здесь запрос в БД
        _checkpoints.add(checkPoint)
    }

    fun remove(
        checkpoint : CCheckPoint
    )
    {
        //Здесь запрос в БД
        _checkpoints.remove(checkpoint)
    }

}