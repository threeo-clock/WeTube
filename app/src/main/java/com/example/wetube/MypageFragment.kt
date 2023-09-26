package com.example.wetube

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wetube.databinding.FragmentMypageBinding
//itemImageView.clipToOutline = true //이미지뷰 radius 적용

class MypageFragment : Fragment() {
    private lateinit var myPageFragment: Context

    private var myPageBinding: FragmentMypageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }
}