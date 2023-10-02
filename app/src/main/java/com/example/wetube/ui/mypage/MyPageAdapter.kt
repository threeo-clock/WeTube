package com.example.wetube.ui.mypage

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.databinding.FragmentSearchItemBinding
import com.example.wetube.databinding.ItemMypageFavoriteVideosBinding

class MyPageAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = ArrayList<MypageItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolder = holder as ItemViewHolder
        viewHolder.bind(item)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    inner class ItemViewHolder(val binding: FragmentSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MypageItem) = binding.apply {
            Glide.with(binding.root)
                .load(Uri.parse(item.thumbnail))
                .fitCenter()
                .override(500, 400)
                .into(imageView)
            searchText.text = item.title
        }
    }
}