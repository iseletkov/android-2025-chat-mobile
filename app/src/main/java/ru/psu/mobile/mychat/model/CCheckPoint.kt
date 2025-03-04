package ru.psu.mobile.mychat.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.UUID


@Entity(tableName = "checkpoints")
data class CCheckPoint (
    @Json(name = "id")
    @PrimaryKey
    var id : UUID,
    @Json(name = "name")
    @ColumnInfo
    var name : String = "",
    @Json(name = "lat")
    @ColumnInfo
    var lat : String? = "",
    @Json(name = "lon")
    @ColumnInfo
    var lon : String? = "",

//    var photos : List<CPhoto>
)
