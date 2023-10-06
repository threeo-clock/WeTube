package com.example.wetube.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.databinding.FragmentHomeBinding
import com.example.wetube.model.ChannelItem
import com.example.wetube.repository.RepositoryHomeVideos
import com.example.wetube.viewmodel.HomeViewModel
import com.example.wetube.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomePopularAdapter
    private lateinit var categoryAdapter: HomeCategoryAdapter
    private lateinit var channelAdapter: HomeChannelAdapter

    private lateinit var homeViewModel: HomeViewModel

    private var originData: MutableList<ChannelItem> = mutableListOf(
        ChannelItem(
            "https://i.ytimg.com/an_webp/mHNCM-YALSA/mqdefault_6s.webp?du=3000&sqp=CKDZ-agG&rs=AOn4CLB2XUUzqY1yrmVuJdgfxOv81xR-PQ",
            "정국 (Jung Kook) '3D (feat. Jack Harlow)' Official MV",
            "test",
            "Music"
        ),
        ChannelItem(
            "https://i.ytimg.com/vi/_Hu4GYtye5U/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLA8N7XMh_hiyJZobPJ7gbu943YwWA",
            "IVE 아이브 'Either Way’ MV\n",
            "test",
            "Music"
        ),
        ChannelItem(
            "https://i.ytimg.com/vi/EIz09kLzN9k/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCSe58umeHjJgBjSHjiTkm6OcF6Mw",
            "AKMU - ‘Love Lee’ M/V\n",
            "test",
            "Music"
        ),
        ChannelItem(
            "https://i.ytimg.com/vi/_GgIt2EFHV8/hqdefault.jpg?sqp=-oaymwEcCPYBEIoBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLAvhyfkd0KJ_VV-eC9sIPOZqgGISA",
            "JEON SOMI (전소미) - ‘Fast Forward’ M/V\n",
            "test",
            "Music"
        ),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomePopularAdapter(requireActivity())
        binding.homeRvVideos.adapter = homeAdapter

        categoryAdapter = HomeCategoryAdapter(requireActivity())
        binding.homeRvCategory.adapter = categoryAdapter

        channelAdapter = HomeChannelAdapter(requireActivity())
        binding.homeRvChannels.adapter = channelAdapter

        val repositoryHomeVideos = RepositoryHomeVideos()
        val homeViewModelFactory = HomeViewModelFactory(repositoryHomeVideos)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()
//        homeViewModel.fetchYoutubeData()
        homeViewModel.getCategoryVideos("selectedCategory")

        homeViewModel.popularVideosResult.observe(viewLifecycleOwner) { items ->
            homeAdapter.items.addAll(items)
            homeAdapter.notifyDataSetChanged()
        }

        homeViewModel.videoCategories.observe(viewLifecycleOwner) { items ->
            val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, items ?: emptyList())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.homeSpinnerCategory.adapter = adapter
            binding.homeSpinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                    val selectedCategory = parent?.getItemAtPosition(position) as String
//                    val filteredData =
//                        homeViewModel.originalData.value?.filter { item -> item.channelTitle == selectedCategory }
////                    val channelItemsList = homeViewModel.originalData.value
////
////                    channelItemsList?.let {
////                        categoryAdapter.items.addAll(it)
////                        Log.d(
////                            "test1234", categoryAdapter.items.addAll(it).toString()
////                        )
////                    }
////                    categoryAdapter.items.addAll(originData)
////                    categoryAdapter.items.clear()
//                    if (filteredData != null) {
//                        categoryAdapter.items.addAll(filteredData)
//                    }
//                    categoryAdapter.notifyDataSetChanged()
//                    homeViewModel.fetchYoutubeData()
//                    homeViewModel.getCategoryVideos(selectedCategory)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
