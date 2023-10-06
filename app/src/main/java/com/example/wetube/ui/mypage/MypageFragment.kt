package com.example.wetube.ui.mypage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wetube.databinding.FragmentMypageBinding
import com.example.wetube.viewmodel.LikesViewModel

//itemImageView.clipToOutline = true //이미지뷰 radius 적용

class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private lateinit var mpAdapter: MyPageAdapter
    private val likesViewModel: LikesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mpAdapter = MyPageAdapter(requireActivity())
        binding.favoriteRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = mpAdapter
        }

        likesViewModel.likedVideosLiveData.observe(viewLifecycleOwner) { items ->
            mpAdapter.items.clear()
            mpAdapter.items.addAll(items)
            mpAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        likesViewModel.refreshLikedVideos()
    }
}