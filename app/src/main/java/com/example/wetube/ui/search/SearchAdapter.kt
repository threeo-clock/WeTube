package com.example.wetube.ui.search

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.databinding.ItemVideoBinding
import com.example.wetube.model.NewList

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<NewList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItems {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchItems(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolderType2 = holder as SearchItems
        viewHolderType2.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class SearchItems(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewList) = binding.apply {
            Glide.with(binding.root)
                .load(Uri.parse(item.thumbnail))
                .fitCenter()
                .override(500, 400)
                .into(itemVideoIvThumbnail)
            itemVideoTvTitle.text = item.title
        }
    }
}