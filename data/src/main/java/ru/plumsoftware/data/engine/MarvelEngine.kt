package ru.plumsoftware.data.engine

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.plumsoftware.data.api.MarvelApi
import ru.plumsoftware.data.constant.ApiConstants

fun marvelEngine(): MarvelApi {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(MarvelApi::class.java)
}