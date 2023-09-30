package com.example.wetube.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val repositoryHomeVideos = RepositoryHomeVideos()
        val homeViewModelFactory = HomeViewModelFactory(repositoryHomeVideos)
        homeViewModel = ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()

        homeViewModel.popularVideosResult.observe(viewLifecycleOwner) { items ->
            homeAdapter.items.addAll(items)
            homeAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}