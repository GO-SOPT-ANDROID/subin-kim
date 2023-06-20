package org.android.go.sopt.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.signInService
import org.android.go.sopt.data.remote.model.RequestSignInDto
import org.android.go.sopt.data.remote.model.ResponseSignInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    private val _signInResult: MutableLiveData<ResponseSignInDto> = MutableLiveData()
    val signInResult: LiveData<ResponseSignInDto> = _signInResult

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun logIn(id: String, password: String) {
        signInService.signIn(
            RequestSignInDto(id, password)
        ).enqueue(object : Callback<ResponseSignInDto> {
            override fun onResponse(
                call: Call<ResponseSignInDto>,
                response: Response<ResponseSignInDto>
            ) {
                if (response.isSuccessful) {
                    _signInResult.value = response.body()
                } else {
                    // TODO: 에러 처리
                }
            }

            override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                // TODO : 실패 처리
            }

        })
    }
}