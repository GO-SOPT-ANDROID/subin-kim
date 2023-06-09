package org.android.go.sopt.signup

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        hideKeyboard()

        viewModel.signUpResult.observe(this) { signUpResult ->
            if (!isFinishing) finish()
            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
        }
        viewModel.errorResult.observe(this) { errorResult ->
            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
        }


        with(binding) {
            btnSignup.setOnClickListener {
                completeSignUp()
            }

            btnSignup.isEnabled = false
            btnSignup.setBackgroundColor(Color.GRAY)

            edtSignupId.addTextChangedListener(textWatcher)
            edtSignupPw.addTextChangedListener(textWatcher)
        }


    }

    private fun completeSignUp() {
        if (!viewModel.checkId(binding.edtSignupId.text.toString())) {
            Toast.makeText(this, "아이디는 영문, 숫자가 포함해서 6~10글자 이내로 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (!viewModel.checkPw(binding.edtSignupPw.text.toString())) {
            Toast.makeText(this, "비밀번호는 영문, 숫자, 특수문자를 포함해 6~12글자 이내로 입력해주세요.", Toast.LENGTH_SHORT)
                .show()
        } else {
            with(binding) {
                viewModel!!.signup(
                    edtSignupId.text!!.toString(),
                    edtSignupPw.text!!.toString(),
                    edtSignupName.text!!.toString(),
                    edtSignupForte.text!!.toString()
                )
            }
        }

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

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            with(binding) {
                val id = edtSignupId.text.toString()
                val password = edtSignupPw.text.toString()

                val isIdValid = viewModel!!.checkId(id)
                val isPasswordValid = viewModel!!.checkPw(password)

                btnSignup.isEnabled = isIdValid && isPasswordValid

                if (!isIdValid) {
                    edtSignupId.error = "아이디는 영문, 숫자가 포함해서 6~10글자 이내로 입력해주세요."
                }
                if (!isPasswordValid) {
                    edtSignupPw.error = "비밀번호는 영문, 숫자, 특수문자를 포함해 6~12글자 이내로 입력해주세요."
                }
            }
        }

    }
}
