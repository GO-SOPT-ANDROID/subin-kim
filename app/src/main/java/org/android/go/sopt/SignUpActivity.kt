package org.android.go.sopt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideKeyboard()

        clickSignUp()
    }

    private fun clickSignUp() {
        binding.btnSignup.setOnClickListener {
            if (binding.edtSignupId.length() == 0 || binding.edtSignupPw.length() == 0 || binding.edtSignupForte.length() == 0 || binding.edtSignupName.length() == 0) {
                Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.edtSignupId.length() < 6 || binding.edtSignupId.length() > 10) {
                Toast.makeText(this, "아이디는 6~10자리로 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.edtSignupPw.length() < 8 || binding.edtSignupPw.length() > 12) {
                Toast.makeText(this, "비밀번호는 8~12자리로 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", binding.edtSignupId.text.toString())
                intent.putExtra("pw", binding.edtSignupPw.text.toString())
                intent.putExtra("name", binding.edtSignupName.text.toString())
                intent.putExtra("forte", binding.edtSignupForte.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun hideKeyboard() {
        binding.bgSignup.setOnClickListener{
            val v = currentFocus
            if (v != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }
}