package com.example.wetube.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.api.RetrofitClient
import com.example.wetube.databinding.FragmentHomeBinding
import com.example.wetube.repository.RepositoryHomeVideos
import com.example.wetube.viewmodel.HomeViewModel
import com.example.wetube.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var channelAdapter: HomeChannelAdapter
    val apiService = RetrofitClient.apiService

    private lateinit var homeViewModel: HomeViewModel

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

        homeAdapter = HomeAdapter(requireActivity())
        binding.homeRvVideos.adapter = homeAdapter

        binding.homeRvCategory.adapter = homeAdapter

        channelAdapter = HomeChannelAdapter(requireActivity())
        binding.homeRvCategory.adapter = channelAdapter

        val repositoryHomeVideos = RepositoryHomeVideos()
        val homeViewModelFactory = HomeViewModelFactory(repositoryHomeVideos)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()
        homeViewModel.getCategoryVideos()

        homeViewModel.popularVideosResult.observe(viewLifecycleOwner) { items ->
            homeAdapter.items.addAll(items)
            homeAdapter.notifyDataSetChanged()
        }

        homeViewModel.videoCategories.observe(viewLifecycleOwner) { items ->

            val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, items ?: emptyList())
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.homeSpinnerCategory.adapter = adapter

        }


//        clickCategory()
    }

//    private fun clickCategory() {
//        val retrofit = RetrofitClient.apiService
//        retrofit.getHomeCategoryVideos(APIKEY,"KR").
//
//        getCategoryVideos
//
//
//
//        binding.homeSpinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val selectCategory = binding.homeSpinnerCategory.selectedItem as YouTubeCategoryItem
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
