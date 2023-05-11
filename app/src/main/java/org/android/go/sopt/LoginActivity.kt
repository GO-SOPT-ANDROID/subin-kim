package org.android.go.sopt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.data.remote.ApiFactory
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.ServicePool.signUpService
import org.android.go.sopt.data.remote.model.RequestSigninDto
import org.android.go.sopt.data.remote.model.ResponseSigninDto
import org.android.go.sopt.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val loginService = ServicePool.signInService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideKeyboard()

        clickLogin()

        clickSignup()

    }

    private fun clickSignup() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun clickLogin() {
        with(binding) {
            btnLogin.setOnClickListener {
                val id = tvId.text.toString()
                val password = tvPw.text.toString()
                networkLogin()
            }
        }
    }

    private fun networkLogin() {
        val requestLogin = RequestSigninDto(
            id = binding.edtId.text.toString(),
            password = binding.edtPw.text.toString()
        )
        val call: Call<ResponseSigninDto> = ServicePool.signInService.signin(requestLogin)
        call.enqueue(object : Callback<ResponseSigninDto> {
            override fun onResponse(call: Call<ResponseSigninDto>, response: Response<ResponseSigninDto>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("id", data?.id)
                    intent.putExtra("name", data?.name)
                    intent.putExtra("skill", data?.skill)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("loginerror", "onResponse: ")
                }
            }

            override fun onFailure(call: Call<ResponseSigninDto>, t: Throwable) {
                Log.e("NetWorkTest", "error: $t")
            }
        })
    }

    private fun hideKeyboard() {
        binding.bgLogin.setOnClickListener {
            val v = currentFocus
            if (v != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

}