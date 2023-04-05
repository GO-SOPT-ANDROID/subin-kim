package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name").toString()
        val forte = intent.getStringExtra("forte").toString()
        binding.tvMypageName.text = "이름: $name"
        binding.tvMypageForte.text = "특기: $forte"
    }
}