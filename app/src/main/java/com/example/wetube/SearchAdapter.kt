package com.example.wetube

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.example.wetube.databinding.FragmentSearchItemBinding
import com.example.wetube.model.NewList
import com.example.wetube.ui.home.HomeAdapter
import com.google.gson.GsonBuilder
import kotlin.math.max

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<NewList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItems {
        val binding = FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchItems(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewHolderType2 = holder as SearchItems
        viewHolderType2.bind(item)
        Log.d("search Adapter", "bindviewholder")
    }
    override fun getItemCount(): Int {
        return items.size
    }
    inner class SearchItems(val binding: FragmentSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewList) = binding.apply {
            Glide.with(binding.root)
                .load(Uri.parse(item.thumbnail))
                .centerCrop()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(imageView)
            searchText.setHtmlText(item.title)
        }
    }
    fun TextView.setHtmlText(htmlText: String) {
        this.text = Html.fromHtml(htmlText,Html.FROM_HTML_MODE_LEGACY)
    }
}