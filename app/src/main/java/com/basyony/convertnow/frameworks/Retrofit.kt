package com.basyony.convertnow.frameworks

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

fun generateRetrofit(
    baseUrl: String,
    callFactory: CallAdapter.Factory,
    converter: Converter.Factory
): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(callFactory)
        .addConverterFactory(converter)
        .client(client)
        .build()
}
