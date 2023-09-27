package com.example.wetube.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.api.RetrofitClient
import com.example.wetube.databinding.FragmentHomeBinding
import com.example.wetube.viewmodel.HomeViewModel
import com.example.wetube.viewmodel.HomeViewModelFactory
import com.google.gson.GsonBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    val apiService = RetrofitClient.apiService

    private lateinit var homeViewModel : HomeViewModel

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
        binding.rvVideos.adapter = homeAdapter

        val homeViewModelFactory = HomeViewModelFactory(apiService)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()
        Log.d("homefragment", "데이터 가져옴")

        homeViewModel.popularVideosResult.observe(this, Observer {response ->
            if (response != null) {
                //binding.rvVideos = GsonBuilder().setPrettyPrinting().create().toJson(response)
            }
            homeAdapter.notifyDataSetChanged()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}