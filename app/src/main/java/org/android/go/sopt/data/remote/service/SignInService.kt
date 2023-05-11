package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.RequestSigninDto
import org.android.go.sopt.data.remote.model.ResponseSigninDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("sign-in")
    fun signin(
        @Body request: RequestSigninDto
    ) : Call<ResponseSigninDto>
}