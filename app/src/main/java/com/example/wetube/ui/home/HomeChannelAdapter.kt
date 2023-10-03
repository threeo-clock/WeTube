package com.example.wetube.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.databinding.ItemChannelsBinding
import com.example.wetube.model.ChannelItem

class HomeChannelAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val list = arrayListOf<ChannelItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemChannelsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChannelViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        val viewHolderType1 = holder as HomeChannelViewHolder
        viewHolderType1.bind(item)
    }

    inner class HomeChannelViewHolder(val binding: ItemChannelsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChannelItem) = binding.apply {
            Glide.with(binding.root)
            tvChannelName.text = item.title

        }
    }
}