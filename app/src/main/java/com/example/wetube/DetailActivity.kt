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
    private lateinit var videoData: NewList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResult()

        binding.detailSave.bringToFront()

        binding.detailSave.setOnClickListener {
            likesViewModel.toggleLike(videoData.thumbnail, videoData)
            updateLikeButtonState(videoData.thumbnail)
        }

        binding.detailShare.setOnClickListener {
            val share = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, videoData.url
                )
                type = "text/plain"
            }
            startActivity(Intent.createChooser(share, null))
        }

        //더 보기 버튼
        var test = true
        binding.detailMore.setOnClickListener {
            if(test==true) {
                binding.detailSub.maxLines = 2147483647 //Int값 최대치
                binding.detailMore.text = "접기"
                test=false
            } else{
                binding.detailSub.maxLines = 5
                binding.detailMore.text = "펼치기"
                test=true
            }
        }
    }
    private fun updateLikeButtonState(thumbnail: String) {
        if (likesViewModel.isVideoLiked(thumbnail)) {
            binding.detailSave.setImageResource(R.drawable.detail_iv_heart_fill)
            Toast.makeText(this, "마이페이지에 저장되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            binding.detailSave.setImageResource(R.drawable.detail_iv_heart)
            Toast.makeText(this, "마이페이지에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setResult() {
        val videoDataJson = intent.getStringExtra("videoData")
        val gson = Gson()
        videoData = gson.fromJson(videoDataJson, NewList::class.java)

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

    //디테일에서 메인으로 돌아갈때 슬라이드 효과
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_slide_up, R.anim.exit_slide_up)
    }
}