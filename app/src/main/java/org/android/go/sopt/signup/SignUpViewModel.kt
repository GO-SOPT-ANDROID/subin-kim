package org.android.go.sopt.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.local.RequestSignUpDto
import org.android.go.sopt.data.local.ResponseSignUpDto
import org.android.go.sopt.data.remote.ServicePool.signUpService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    private val _errorResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val errorResult: LiveData<ResponseSignUpDto> = _errorResult

    fun signup(id: String, password: String, name: String, forte: String) {
        signUpService.signUp(
            RequestSignUpDto(id, password, name, forte)
        ).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                } else {
                    _errorResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
            }

        })
    }
}