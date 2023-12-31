package com.example.wetube

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.wetube.databinding.ActivityMainBinding
import com.example.wetube.ui.home.HomeFragment
import com.example.wetube.ui.mypage.MypageFragment


// https://teamsparta.notion.site/e9cce6173ed04fbc871da4818668c4e6
class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var mypageFragment: MypageFragment
private var mBackWait:Long = 0
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNavigationView()

        this.onBackPressedDispatcher.addCallback(this, callback)

    }

    private fun setUpBottomNavigationView() = binding.apply {
        homeFragment = HomeFragment()
        mypageFragment = MypageFragment()

        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.run {
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
        btnSearch.setOnClickListener {
            this@MainActivity.supportFragmentManager
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

    fun setSelectedNavItem(itemId: Int) {
        binding.bottomNavigationView.selectedItemId = itemId
    }

    //디테일 액티비티로 넘어갈때 애니메이션 효과
    override fun onPause() {
        super.onPause()
        overridePendingTransition(R.anim.enter_slide_down, R.anim.exit_slide_down)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(System.currentTimeMillis() - mBackWait >= 2000) {
                mBackWait = System.currentTimeMillis()
                Toast.makeText(this@MainActivity, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                finish()
            }
        }
    }
}