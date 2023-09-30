package com.example.wetube.ui.search

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wetube.Item
import com.example.wetube.R
import com.example.wetube.databinding.FragmentSearchBinding

class SearchFragment : DialogFragment() {
    private lateinit var binding: FragmentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val dataList = mutableListOf<Item>()

        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))
        dataList.add(Item(R.drawable.img, "귀여운 강아지 한번 보고 가세요. 솜사탕 머리의 강아지"))

        val adapter = SearchAdapter(dataList)
        binding.searchRecyclerView.adapter = SearchAdapter(dataList)
        binding.searchRecyclerView.adapter = adapter
        binding.searchRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.ivSearchBack.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}