package org.android.go.sopt

import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://52.78.152.187:8080/")
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)


    object ServicePool {
        val signUpService = ApiFactory.create<SignUpService>()
    }


}