package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.data.multi_type1
import org.android.go.sopt.data.multi_type2
import org.android.go.sopt.databinding.ActivityMain2Binding
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.fragment.GalleryFragment
import org.android.go.sopt.fragment.HomeFragment
import org.android.go.sopt.fragment.SearchFragment

class Main2Activity : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var binding_home: FragmentHomeBinding
    lateinit var multiAdapter: MultiviewAdapter
    val datas = mutableListOf<MultiData>()

    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main2)
         initRecycler()
     }
     private fun initRecycler() {
         multiAdapter = MultiviewAdapter(this)
         binding.rvHome.adapter = multiAdapter
     }*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()

        //val a = HomeFragment()

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_main, HomeFragment()).commit()
        }

        binding.bnvMain.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    changeFragment(GalleryFragment())
                    return@setOnItemSelectedListener true
                }
            }

        }
    }

    private fun initRecycler() {
        multiAdapter = MultiviewAdapter(this)
        with(binding_home) {
            rvHome.adapter = multiAdapter
        }

        datas.apply {
            add(
                MultiData(
                    type = multi_type1,
                    title = R.string.home_title,
                    image = null,
                    repo = null,
                    name = null
                )
            )
            add(
                MultiData(
                    type = multi_type2,
                    title = null,
                    image = R.drawable.dog,
                    repo = R.string.home_repo,
                    name = null
                )
            )

            multiAdapter.datas = datas
            multiAdapter.notifyDataSetChanged()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}