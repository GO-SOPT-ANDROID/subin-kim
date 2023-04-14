package org.android.go.sopt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var forte: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideKeyboard()

        setResultSignUp()

        clickLogin()

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            // 데이터를 받아올 activity 실행 (startActivityForResult와 동일한 기능)
            resultLauncher.launch(intent)
        }

    }

    private fun setResultSignUp() {
        // Callback 등록
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id").toString()
                pw = result.data?.getStringExtra("pw").toString()
                name = result.data?.getStringExtra("name").toString()
                forte = result.data?.getStringExtra("forte").toString()

                Snackbar.make(binding.root, "회원가입에 성공했습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickLogin() {
        binding.btnLogin.setOnClickListener {
            if (binding.edtId.text.toString() == id && binding.edtPw.text.toString() == pw) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MypageActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("forte", forte)
                startActivity(intent)
            } else {
                Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideKeyboard() {
        binding.bgMain.setOnClickListener{
            val v = currentFocus
            if (v != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

}