package com.example.giphyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GiphyRecyclerViewAdapter(
    private val context: Context
) : PagingDataAdapter<DataObject, GiphyRecyclerViewAdapter.GiphyViewHolder>(GifsDiffCallBack()) {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.gif_layout, parent, false)
        return GiphyViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val data = getItem(position)?.images?.originalImage?.url
        Glide.with(context).load(data)
            .error(R.drawable.ic_launcher_foreground).into(holder.gifImageView)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun getItemAtPosition(position: Int): OriginalImage? {
        return getItem(position)?.images?.originalImage
    }

    class GifsDiffCallBack : DiffUtil.ItemCallback<DataObject>() {
        override fun areItemsTheSame(oldItem: DataObject, newItem: DataObject): Boolean {
            return oldItem.images.originalImage.url == newItem.images.originalImage.url
        }

        override fun areContentsTheSame(oldItem: DataObject, newItem: DataObject): Boolean {
            return oldItem == newItem
        }
    }

    class GiphyViewHolder(
        itemView: View,
        private val listener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var gifImageView: ImageView

        init {
            gifImageView = itemView.findViewById(R.id.gif_iv)
        }

        override fun onClick(v: View?) {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}