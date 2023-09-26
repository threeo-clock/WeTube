package com.example.wetube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wetube.databinding.FragmentSearchItemBinding

class SearchAdapter(val item: MutableList<Item>) : RecyclerView.Adapter<SearchAdapter.SearchItems>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItems {
        val binding = FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchItems(binding)
    }

    override fun onBindViewHolder(holder: SearchItems, position: Int) {
        holder.image.setImageResource(item[position].aImage)
        holder.name.text = item[position].aName
    }

    override fun getItemCount(): Int {
        return item.size
    }
    inner class SearchItems(val binding: FragmentSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView
        val name = binding.searchText
    }
}