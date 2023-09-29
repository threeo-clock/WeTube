package com.example.wetube.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.DetailActivity
import com.example.wetube.databinding.FragmentSearchItemBinding
import com.example.wetube.model.NewList

class HomeAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<NewList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeVideoViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolderType1 = holder as HomeVideoViewHolder
        viewHolderType1.bind(item)
    }

    inner class HomeVideoViewHolder(val binding: FragmentSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewList) = binding.apply {
            Glide.with(binding.root)
                .load(Uri.parse(item.thumbnail))
                .fitCenter()
                .override(500, 400)
                .into(imageView)
            searchText.text = item.title

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, DetailActivity::class.java)
                itemView.context.startActivity(myIntent)
            }
        }
    }
}