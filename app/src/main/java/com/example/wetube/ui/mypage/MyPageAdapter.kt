package com.example.wetube.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wetube.ItemModel
import com.example.wetube.databinding.ItemBinding

class MyPageAdapter(var mpContext: Context) : RecyclerView.Adapter<MyPageAdapter.ItemViewHolder>() {
    var items = mutableListOf<ItemModel>()
//    var likedItems: ArrayList<ItemModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // 이미지 로딩 라이브러리(Glide)를 사용해 썸네일 이미지를 로드한다.
        Glide.with(mpContext)
            .load(items[position].url)
            .into((holder).iv_video)

        holder.tv_title.text = items[position].title

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var tv_title: TextView = binding.tvTitle
        var iv_video: ImageView = binding.ivVideo
        val item:ConstraintLayout = binding.item

        init {
            item.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

//    fun removeLikedItem(item: ItemModel) {
//        likedItems.remove(item)
//    }

}