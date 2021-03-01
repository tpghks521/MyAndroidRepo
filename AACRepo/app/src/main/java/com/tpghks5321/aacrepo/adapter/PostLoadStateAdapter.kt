package com.tpghks5321.stunitasproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tpghks5321.aacrepo.R
import com.tpghks5321.aacrepo.databinding.NetworkStateItemBinding

class PostLoadStateAdapter(
) : LoadStateAdapter<NetworkStateItemViewHolder>() {
	override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
		holder.bindTo(loadState)
	}

	override fun onCreateViewHolder(
			parent: ViewGroup,
			loadState: LoadState
	): NetworkStateItemViewHolder {
		return NetworkStateItemViewHolder(parent)
	}

}

class NetworkStateItemViewHolder(
		parent: ViewGroup
) : RecyclerView.ViewHolder(
		LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {
	private val binding = NetworkStateItemBinding.bind(itemView)

	fun bindTo(loadState: LoadState) {
		binding.progressBar.isVisible = loadState is LoadState.Loading
	}
}