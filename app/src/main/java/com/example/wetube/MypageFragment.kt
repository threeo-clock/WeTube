package com.example.wetube

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wetube.databinding.FragmentMypageBinding
//itemImageView.clipToOutline = true //이미지뷰 radius 적용

class MypageFragment : Fragment() {
    private lateinit var mpContext: Context

    private var mpBinding: FragmentMypageBinding? = null
    private lateinit var mpAdapter: MyPageAdapter
    var likedItems: ArrayList<ItemModel> = ArrayList()

    private var mpItems: List<ItemModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mpContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_inbox, container, false)
        likedItems

        Log.d("InboxFragment", "InboxItems Size = ${mpItems.size}")

        mpAdapter = MyPageAdapter(mpContext).apply {
            items = mpItems.toMutableList()
        }

        mpBinding = FragmentMypageBinding.inflate(inflater, container, false).apply {
            favoriteRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            favoriteRecyclerview.adapter = mpAdapter
        }

        return mpBinding?.root
    }

}