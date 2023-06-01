package org.android.go.sopt.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val headerRequest = originalRequest.newBuilder()
            .header("Authorization", "/* 토큰의 실제 값! */")
            .build()

        return chain.proceed(headerRequest)
    }
}