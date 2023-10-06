package com.example.wetube.ui.mypage

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.DetailActivity
import com.example.wetube.databinding.ItemVideoFullBinding
import com.example.wetube.model.NewList
import com.google.gson.GsonBuilder

class MyPageAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = ArrayList<NewList>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemVideoFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ItemViewHolder(val binding: ItemVideoFullBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewList) = binding.apply {
                if (ivVideoFullThumbnail != null) {
                    Glide.with(binding.root)
                        .load(Uri.parse(item.thumbnail))
                        .fitCenter()
                        .override(500, 400)
                        .into(ivVideoFullThumbnail)
                }

            tvVideoFullTitle?.text = item.title

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, DetailActivity::class.java)
                val gson = GsonBuilder().create()
                val videoData = gson.toJson(items[bindingAdapterPosition])
                myIntent.putExtra("videoData", videoData)
                itemView.context.startActivity(myIntent)
            }
        }
    }
}