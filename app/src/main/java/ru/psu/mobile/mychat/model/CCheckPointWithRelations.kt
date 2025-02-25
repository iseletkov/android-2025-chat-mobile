package ru.psu.mobile.mychat.model

import androidx.room.Embedded
import androidx.room.Relation

data class CCheckPointWithRelations(
    @Embedded
    val checkpoint: CCheckPoint,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkpoint"
    )
    val photos: List<CPhoto>

)