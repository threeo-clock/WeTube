package com.example.wetube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wetube.api.RetrofitClient
import com.example.wetube.databinding.FragmentHomeBinding
import com.example.wetube.viewmodel.HomeViewModel
import androidx.fragment.app.Fragment
import com.example.wetube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    val apiService = RetrofitClient.apiService

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeAdapter(requireActivity())
        binding.rvVideos.adapter = homeAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()
        homeViewModel.popularVideosResult.observe(this, Observer {
            // val adapter = Adapter(this, it)
        })
    }
}