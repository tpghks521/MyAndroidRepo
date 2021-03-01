package com.tpghks5321.aacrepo.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.tpghks5321.aacrepo.R

@BindingAdapter("imageLoad")
fun bindImageLoad(view: ImageView, url: String?) {
	Glide.with(view).load(url).listener(
			object : RequestListener<Drawable> {
				override fun onLoadFailed(
						e: GlideException?,
						model: Any?,
						target: Target<Drawable>?,
						isFirstResource: Boolean
				): Boolean {
					view.setImageResource(R.drawable.ic_launcher_background)
					return true
				}

				override fun onResourceReady(
						resource: Drawable?,
						model: Any?,
						target: Target<Drawable>?,
						dataSource: DataSource?,
						isFirstResource: Boolean
				): Boolean {
					return false
				}
			}
	).into(
			view
	)
}