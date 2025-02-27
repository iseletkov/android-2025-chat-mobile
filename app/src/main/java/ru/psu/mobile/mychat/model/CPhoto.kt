package ru.psu.mobile.mychat.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "photos",
    foreignKeys = [ForeignKey(
        entity = CCheckPoint::class,
        parentColumns = ["id"],
        childColumns = ["checkpoint_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CPhoto
(
    @PrimaryKey
    var id : UUID,

    @ColumnInfo
    var name: String,

    @ColumnInfo(name="checkpoint_id")
    var checkpoint : UUID,

    @Ignore
    var bitmap: Bitmap?
            = null

){
    constructor(id: UUID, name: String, checkpoint: UUID)
            : this(id, name, checkpoint, null)
}