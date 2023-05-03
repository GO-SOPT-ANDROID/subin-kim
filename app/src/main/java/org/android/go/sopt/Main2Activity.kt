package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityMain2Binding
import org.android.go.sopt.fragment.GalleryFragment
import org.android.go.sopt.fragment.HomeFragment
import org.android.go.sopt.fragment.MypageFragment
import org.android.go.sopt.fragment.SearchFragment

class Main2Activity: AppCompatActivity(){
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val a = HomeFragment()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if(currentFragment == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main,HomeFragment()).commit()
        }

        binding.bnvMain.setOnItemSelectedListener{ item ->

                when(item.itemId){
                    R.id.menu_home -> {
                        changeFragment(HomeFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_search -> {
                        changeFragment(SearchFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.menu_gallery -> {
                        changeFragment(GalleryFragment())
                        return@setOnItemSelectedListener true
                    }
                    else -> {
                        changeFragment(MypageFragment())
                        return@setOnItemSelectedListener true
                    }
                }

        }
    }

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main,fragment)
            .commit()
    }
}