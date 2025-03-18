package ru.psu.mobile.mychat.util.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "http://192.168.1.4:50880"

val moshi : Moshi = Moshi.Builder()
    .add(CAdapterUUID())
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object CAPICheckPoints {
    val retrofitService : IAPICheckPoints by lazy {
        retrofit.create(IAPICheckPoints::class.java)
    }
}
