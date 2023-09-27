package com.example.wetube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wetube.databinding.ActivityDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

//제목, 내용, 썸네일이 출력됨
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_detail)


        binding.detailSave.setOnClickListener{
            SaveData()
            Toast.makeText(this, "저장버튼 클릭", Toast.LENGTH_SHORT).show()
        }
        LoadData()
        binding.detailShare.setOnClickListener{
            Toast.makeText(this,"공유버튼 클릭",Toast.LENGTH_SHORT).show()
        }
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