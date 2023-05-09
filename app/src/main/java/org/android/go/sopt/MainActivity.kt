package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.fragment.GalleryFragment
import org.android.go.sopt.fragment.HomeFragment
import org.android.go.sopt.fragment.MypageFragment
import org.android.go.sopt.fragment.SearchFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, HomeFragment()).commit()
        }


        with(binding) {
            bnvMain.setOnItemSelectedListener { item ->

                if (bnvMain.selectedItemId == item.itemId) {
                    fcvMain.scrollTo(0, 0)
                }

                when (item.itemId) {
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
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}
