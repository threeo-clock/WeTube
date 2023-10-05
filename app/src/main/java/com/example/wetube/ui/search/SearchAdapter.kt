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
import com.example.wetube.databinding.ItemVideoBinding
import com.example.wetube.databinding.ItemVideoFullBinding
import com.example.wetube.model.NewList
import com.google.gson.GsonBuilder

class SearchAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = ArrayList<NewList>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItems {
        val binding = ItemVideoFullBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class SearchItems(val binding: ItemVideoFullBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewList) = binding.apply {
            if (ivVideoFullThumbnail != null) {
                Glide.with(binding.root)
                    .load(Uri.parse(item.thumbnail))
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .into(ivVideoFullThumbnail)
            }
            tvVideoFullTitle?.setHtmlText(item.title)

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, DetailActivity::class.java)
                val gson = GsonBuilder().create()
                val videoData = gson.toJson(items[bindingAdapterPosition])
                myIntent.putExtra("videoData", videoData)
                itemView.context.startActivity(myIntent)
            }
        }
    }

    fun TextView.setHtmlText(htmlText: String) {
        this.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    }
}