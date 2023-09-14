package com.example.giphyapp.feature.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.giphyapp.feature.data.remote.model.DataObject
import com.example.giphyapp.databinding.GifLayoutBinding

class GiphyRecyclerViewAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<DataObject, GiphyRecyclerViewAdapter.GiphyViewHolder>(GifsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val binding = GifLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class GifsDiffCallBack : DiffUtil.ItemCallback<DataObject>() {
        override fun areItemsTheSame(oldItem: DataObject, newItem: DataObject): Boolean {
            return oldItem.images.originalImage.url == newItem.images.originalImage.url
        }

        override fun areContentsTheSame(oldItem: DataObject, newItem: DataObject): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(gif: String)
    }

    inner class GiphyViewHolder(
        private val binding: GifLayoutBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)

                    if (item != null)
                        listener.onItemClick(item.images.originalImage.url)
                }
            }
        }

        fun bind(data: DataObject) {

            val drawable = CircularProgressDrawable(itemView.context).apply {
                centerRadius = 64f
                strokeWidth = 8f
                start()
            }

            binding.apply {
                Glide.with(itemView)
                    .load(data.images.originalImage.url)
                    .placeholder(drawable)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(gifIv)
            }
        }
    }
}