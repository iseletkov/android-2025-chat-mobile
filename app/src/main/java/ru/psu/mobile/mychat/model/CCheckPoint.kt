package ru.psu.mobile.mychat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "checkpoints")
data class CCheckPoint (
    @PrimaryKey
    var id : UUID,
    @ColumnInfo
    var name : String,
    @ColumnInfo
    var lat : String,
    @ColumnInfo
    var lon : String
)
