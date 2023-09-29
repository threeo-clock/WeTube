package com.example.wetube

import android.app.Instrumentation.ActivityResult
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wetube.databinding.ActivityDetailBinding
import com.example.wetube.model.NewList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

//제목, 내용, 썸네일이 출력됨
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        setResult()
        
        binding.detailSave.setOnClickListener{
            SaveData()
            Toast.makeText(this, "저장버튼 클릭", Toast.LENGTH_SHORT).show()
        }
        LoadData()

        binding.detailShare.setOnClickListener{
            Toast.makeText(this,"공유버튼 클릭",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResult() {
        val videoDataJson = intent.getStringExtra("videoData")
        val gson = Gson()
        val videoData = gson.fromJson(videoDataJson, NewList::class.java)

        if(videoData != null) {
            binding.detailTitle.text = videoData.title
            Glide.with(this)
                .load(Uri.parse(videoData.thumbnail))
                .fitCenter()
                .override(500,400)
                .into(binding.detailImg)
            binding.detailSub.text = videoData.description
        }
        
    private fun SaveData(){
        val pref = getSharedPreferences("pref",0)
        val edit = pref.edit()

        edit.putString("Title",binding.detailTitle.text.toString())
        edit.putString("Sub",binding.detailSub.text.toString())
        edit.apply()
    }
    private fun LoadData(){
        val pref = getSharedPreferences("pref",0)
        binding.detailTitleTest.text = pref.getString("Title","")
        binding.detailSubTest.text = pref.getString("Sub","")
    }
}