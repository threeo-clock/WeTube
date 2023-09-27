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

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
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

        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.getPopularVideosData()
        homeViewModel.popularVideosResult.observe(this, Observer {
            // val adapter = Adapter(this, it)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}