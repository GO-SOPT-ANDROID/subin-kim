package org.android.go.sopt

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.data.local.RequestSignUpDto
import org.android.go.sopt.data.local.ResponseSignUpDto
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private val signUpService = ServicePool.signUpService
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideKeyboard()

        binding.btnSignup.setOnClickListener {
            completeSignUp()
        }
    }

    private fun completeSignUp() {
        if (binding.edtSignupId.length() == 0 || binding.edtSignupPw.length() == 0 || binding.edtSignupForte.length() == 0 || binding.edtSignupName.length() == 0) {
            Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.edtSignupId.length() < 6 || binding.edtSignupId.length() > 10) {
            Toast.makeText(this, "아이디는 6~10자리로 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (binding.edtSignupPw.length() < 8 || binding.edtSignupPw.length() > 12) {
            Toast.makeText(this, "비밀번호는 8~12자리로 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            signUpService.signUp(
                with(binding) {
                    RequestSignUpDto(
                        edtSignupId.text.toString(),
                        edtSignupPw.text.toString(),
                        edtSignupName.text.toString(),
                        edtSignupForte.text.toString()
                    )
                }
            ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.message?.let { makeToastMessage(it) } ?: "회원가입에 성공했습니다."

                        if (!isFinishing) finish()
                    } else {

                        response.body()?.message?.let { makeToastMessage(it) } ?: "서버통신 실패(40X)"
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {

                    t.message?.let { makeToastMessage(it) } ?: "서버통신 실패(응답값 X)"
                }
            })
        }

    }

    private fun makeToastMessage(message: String, context: Context = applicationContext) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        binding.bgSignup.setOnClickListener {
            val v = currentFocus
            if (v != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }
}