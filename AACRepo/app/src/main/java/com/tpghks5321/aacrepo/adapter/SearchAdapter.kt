package com.tpghks5321.stunitasproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tpghks5321.aacrepo.data.KakaoImage
import com.tpghks5321.aacrepo.databinding.ItemSearchBinding

class SearchAdapter :
    PagingDataAdapter<KakaoImage, SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoImage) {
            binding.apply {
                kakaoImage = item
                executePendingBindings()
            }
        }
    }

}

private class SearchDiffCallback : DiffUtil.ItemCallback<KakaoImage>() {
    override fun areItemsTheSame(oldItem: KakaoImage, newItem: KakaoImage): Boolean {
        return oldItem.image_url == newItem.image_url
    }

    override fun areContentsTheSame(oldItem: KakaoImage, newItem: KakaoImage): Boolean {
        return false
    }
}