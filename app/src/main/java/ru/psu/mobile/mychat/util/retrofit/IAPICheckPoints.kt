package ru.psu.mobile.mychat.util.retrofit

import retrofit2.http.GET
import ru.psu.mobile.mychat.model.CCheckPoint

interface IAPICheckPoints {
    @GET("checkpoints")
    suspend fun getCheckPoints() : List<CCheckPoint>
}