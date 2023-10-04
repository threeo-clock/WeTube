package com.example.wetube

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.wetube.databinding.ActivityDetailBinding
import com.example.wetube.model.NewList
import com.example.wetube.viewmodel.LikesViewModel

import com.google.gson.Gson

//제목, 내용, 썸네일이 출력됨
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val likesViewModel: LikesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResult()

        //버튼 binding으로 특정하기
        var save = binding.detailSave
        var share = binding.detailShare
        save.bringToFront()
        var check = true

        //버튼 클릭 이벤트 설정
        binding.detailSave.setOnClickListener {
            val videoDataJson = intent.getStringExtra("videoData")
            val videoData = Gson().fromJson(videoDataJson, NewList::class.java)
            likesViewModel.toggleLike(videoData.thumbnail, videoData)
            updateLikeButtonState(videoData.thumbnail)
        }
//        save.setOnClickListener {
//            if (check == true) {
//                //저장버튼 누를때 아이콘이 바뀌고 토스트메세지 출력
//                saveData()
//                Toast.makeText(this, "저장버튼 클릭", Toast.LENGTH_SHORT).show()
//                save.setImageResource(R.drawable.detail_out_icon)
//                check = false
//            } else {
//                saveData()
//                Toast.makeText(this, "저장버튼 해제", Toast.LENGTH_SHORT).show()
//                save.setImageResource(R.drawable.detail_in_icon)
//                check = true
//            }
//        }
//        loadData()

        share.setOnClickListener {
            val share = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, "여기에 URL입력"
                )
                type = "text/plain"
            }
            startActivity(Intent.createChooser(share, null))
        }
    }
    private fun updateLikeButtonState(thumbnail: String) {
        if (likesViewModel.isVideoLiked(thumbnail)) {
            binding.detailSave.setImageResource(R.drawable.detail_in_icon)
            Toast.makeText(this, "마이페이지에 저장되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            binding.detailSave.setImageResource(R.drawable.detail_out_icon)
            Toast.makeText(this, "마이페이지에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResult() {
        val videoDataJson = intent.getStringExtra("videoData")
        val gson = Gson()
        val videoData = gson.fromJson(videoDataJson, NewList::class.java)

        if (videoData != null) {
            binding.detailTitle.setHtmlText(videoData.title)
            Glide.with(this)
                .load(Uri.parse(videoData.thumbnail))
                .fitCenter()
                .override(500, 400)
                .into(binding.detailImg)
            binding.detailSub.setHtmlText(videoData.description)
        }
    }
    fun TextView.setHtmlText(htmlText: String) {
        this.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    }

    //데이터 저장 및 불러오기
    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()

        edit.putString("Title", binding.detailTitle.text.toString())
        edit.putString("Sub", binding.detailSub.text.toString())
        edit.apply()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
    }

    //디테일에서 메인으로 돌아갈때 슬라이드 효과
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_slide_up, R.anim.exit_slide_up)
    }
    //메인에서 디테일로 넘어올때는 아래 코드 필요
    //overridePendingTransition(R.anim.enter_slide_down, R.anim.enter_slide_down)
}