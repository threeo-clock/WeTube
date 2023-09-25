package com.example.wetube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wetube.databinding.ActivityDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

//제목, 내용, 썸네일이 출력됨
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setContentView(R.layout.activity_detail)

        val save = findViewById<FloatingActionButton>(R.id.detail_save)
        save.setOnClickListener {
            Toast.makeText(this, "저장버튼 클릭", Toast.LENGTH_SHORT).show()
        }

//        binding.detailSave.setOnClickListener{
//            Toast.makeText(this,"저장버튼 클릭",Toast.LENGTH_SHORT).show()
//        }
        binding.detailShare.setOnClickListener{
            Toast.makeText(this,"공유버튼 클릭",Toast.LENGTH_SHORT).show()
        }
    }
}