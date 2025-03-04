package ru.psu.mobile.mychat.util.retrofit

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.UUID


class CAdapterUUID {
    @FromJson
    fun fromJson(uuid: String): UUID = UUID.fromString(uuid)

    @ToJson
    fun toJson(value: UUID): String = value.toString()
}