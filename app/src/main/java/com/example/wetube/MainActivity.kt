package com.example.wetube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wetube.databinding.ActivityMainBinding


// https://teamsparta.notion.site/e9cce6173ed04fbc871da4818668c4e6
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNavigationView()
    }
    private fun setUpBottomNavigationView() {
        val host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        //임시: 디테일 페이지로 넘겨주기
       binding.btn.setOnClickListener {
            Toast.makeText(this,"액티비티 토스",Toast.LENGTH_SHORT).show()
            val toss = Intent(this,DetailActivity::class.java)
            startActivity(toss)
        }

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}