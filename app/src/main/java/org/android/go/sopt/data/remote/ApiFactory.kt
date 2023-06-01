package org.android.go.sopt.data.remote

import android.os.Build
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.service.ImageService
import org.android.go.sopt.data.remote.service.ReqresService
import org.android.go.sopt.data.remote.service.SignInService
import org.android.go.sopt.data.remote.service.SignUpService
import retrofit2.Retrofit
import java.io.IOException

object ApiFactory {
    private const val BASE_URL = BuildConfig.AUTH_BASE_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ReqresApi {
    private const val BASE_URL = BuildConfig.REQRES_BASE_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    }



    val retrofitForImage : Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())).build()
    }
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}


object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val signInService = ApiFactory.create<SignInService>()
    val reqresService = ReqresApi.create<ReqresService>()
    val imageService = ApiFactory.create<ImageService>()
}