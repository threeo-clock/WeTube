package com.example.wetube

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wetube.databinding.ActivityMainBinding


// https://teamsparta.notion.site/e9cce6173ed04fbc871da4818668c4e6
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNavigationView()
    }

    private fun setUpBottomNavigationView() {
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.fragment_home -> changeFragment(HomeFragment())
                    R.id.fragment_mypage -> changeFragment(MypageFragment())
                }
                true
            }
            selectedItemId = R.id.fragment_home
        }
        //플로팅버튼 연결
        binding.btnSearch.setOnClickListener {
            this.supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, SearchFragment())
                .commit()
        }
    }

    //바텀네비게이션 연결
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commit()
    }

}