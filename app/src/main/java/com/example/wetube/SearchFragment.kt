package com.example.wetube

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wetube.databinding.FragmentHomeBinding
import com.example.wetube.databinding.FragmentSearchBinding
import com.example.wetube.repository.RepositoryHomeVideos
import com.example.wetube.ui.home.HomeAdapter
import com.example.wetube.ui.home.HomeFragment
import com.example.wetube.viewmodel.HomeViewModel
import com.example.wetube.viewmodel.HomeViewModelFactory
import com.example.wetube.viewmodel.SearchViewModel
import com.example.wetube.viewmodel.SearchViewModelFactory

class SearchFragment : Fragment() {
    private var isLoading = false

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter

    private lateinit var searchViewModel : SearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisbleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
                val totalItemCount = recyclerView.adapter!!.itemCount -1

                if (lastVisbleItemPosition == totalItemCount) {
                    val searchText = binding.etSearch.text.toString()
                    if (searchText.isNotEmpty()  && searchViewModel.receivedResults < searchViewModel.totalResults) {
                        isLoading = true
                        searchViewModel.getSearchVideosData(searchText,requireContext())
                    }
                }
            }
        })

        searchAdapter = SearchAdapter(requireActivity())
        binding.searchRecyclerView.apply{
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
        }

        val repositoryHomeVideos = RepositoryHomeVideos()
        val searchViewModelFactory = SearchViewModelFactory(repositoryHomeVideos)
        searchViewModel = ViewModelProvider(this, searchViewModelFactory)[SearchViewModel::class.java]

        binding.btnSearch.setOnClickListener {
            val searchText = binding.etSearch.text.toString()
            if (searchText.isNotEmpty()) {
                binding.clBlankscreen.visibility = View.GONE
                binding.searchRecyclerView.visibility = View.VISIBLE
                searchAdapter.items.clear()
                searchViewModel.getSearchVideosData(searchText,requireContext())
            } else {
                Toast.makeText(requireContext(),"검색어를 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }
        searchViewModel.searchVideosResult.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                Toast.makeText(requireContext(),"검색결과가 없습니다.",Toast.LENGTH_SHORT).show()
            } else {
                searchAdapter.items.addAll(items)
                searchAdapter.notifyDataSetChanged()
            }

            val hideKeyboard = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKeyboard.hideSoftInputFromWindow(_binding?.etSearch!!.windowToken, 0)
        }

        binding.ivSearchBack.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .hide(this@SearchFragment)
                .show(HomeFragment())
                .commit()
            (requireActivity() as MainActivity).setSelectedNavItem(R.id.fragment_home)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}