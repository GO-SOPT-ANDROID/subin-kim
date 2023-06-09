package org.android.go.sopt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.signin.SignInViewModel
import org.android.go.sopt.signup.SignUpActivity


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val loginService = ServicePool.signInService

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideKeyboard()

        clickLogin()

        clickSignup()

        viewModel.signInResult.observe(this) {signInResult ->

            Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            /*intent.putExtra("id", data?.id)
            intent.putExtra("name", data?.name)
            intent.putExtra("skill", data?.skill)*/
            startActivity(intent)
            finish()
        }

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
                networkLogin()
            }
        }
    }

    private fun networkLogin() {
        with(binding) {
            viewModel.logIn(
                edtId.text.toString(),
                edtPw.text.toString()
            )
        }
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