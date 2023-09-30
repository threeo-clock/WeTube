package com.example.wetube.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.DetailActivity
import com.example.wetube.databinding.ItemChannelsBinding
import com.example.wetube.databinding.ItemVideoBinding
import com.example.wetube.model.NewList
import com.google.gson.GsonBuilder

class HomeChannelAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = Data.list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemChannelsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChannelViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolderType1 = holder as HomeChannelViewHolder
        viewHolderType1.bind(item)
    }

    inner class HomeChannelViewHolder(val binding: ItemChannelsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Video) = binding.apply {
            Glide.with(binding.root)
            tvChannelName.text = item.title

        }
    }
}