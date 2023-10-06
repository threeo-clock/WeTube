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
                    val selectedCategory = parent?.getItemAtPosition(position)
                    Log.d("test12345555", selectedCategory.toString())
                    homeViewModel.originalData.value?.filter { item ->
                        item.channelId == selectedCategory
                    }
                    Log.d("test12333333", homeViewModel.originalData.value.toString())
                    categoryAdapter.notifyDataSetChanged()
                    categoryAdapter.items.clear()
                    categoryAdapter.items.addAll(homeViewModel.originalData.value?.toMutableList() ?: mutableListOf())
                    homeViewModel.fetchYoutubeData(
                        when (selectedCategory.toString()) {
                            "Film & Animation" -> 1
                            "Autos & Vehicles" -> 2
                            "Music" -> 10
                            "Pets & Animals" -> 15
                            "Sports" -> 17
                            "Short Movies" -> 18
                            "Travel & Events" -> 19
                            "Gaming" -> 20
                            "Videoblogging" -> 21
                            "People & Blogs" -> 22
                            "Comedy" -> 23
                            "Entertainment" -> 24
                            "News & Politics" -> 25
                            "Howto & Style" -> 26
                            "Education" -> 27
                            "Science & Technology" -> 28
                            "Nonprofits & Activism" -> 29
                            "Movies" -> 30
                            "Anime/Animation" -> 31
                            "Action/Adventure" -> 32
                            "Classics" -> 33
                            "Comedy" -> 34
                            "Documentary" -> 35
                            "Drama" -> 36
                            "Family" -> 37
                            "Foreign" -> 38
                            "Horror" -> 39
                            "Sci-Fi/Fantasy" -> 40
                            "Thriller" -> 41
                            "Shorts" -> 42
                            "Shows" -> 43
                            else -> 44
                        }
                    )
                    Log.d("test123123123", selectedCategory.toString())

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
