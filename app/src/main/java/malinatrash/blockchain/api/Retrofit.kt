package malinatrash.blockchain.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("http://92.51.45.202:8080")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()