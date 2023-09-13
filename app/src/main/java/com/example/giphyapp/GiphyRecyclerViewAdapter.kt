package com.example.giphyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GiphyRecyclerViewAdapter(
    private val context: Context,
    private val gifs: PagingData<DataObject>
) : PagingDataAdapter<OriginalImage, GiphyRecyclerViewAdapter.GiphyViewHolder>(GifsDiffCallBack()) {

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.gif_layout, parent, false)
        return GiphyViewHolder(view, listener, gifs)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val data = getItem(position)
        Glide.with(context).load(data?.url)
            .error(R.drawable.ic_launcher_foreground).into(holder.gifImageView)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun getItemAtPosition(position: Int): OriginalImage? {
        return getItem(position)
    }

    class GifsDiffCallBack : DiffUtil.ItemCallback<OriginalImage>() {
        override fun areItemsTheSame(oldItem: OriginalImage, newItem: OriginalImage): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: OriginalImage, newItem: OriginalImage): Boolean {
            return oldItem == newItem
        }
    }

    class GiphyViewHolder(
        itemView: View,
        private val listener: OnItemClickListener,
        gifs: PagingData<DataObject>
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val gifs: PagingData<DataObject>
        var gifImageView: ImageView

        init {
            gifImageView = itemView.findViewById(R.id.gif_iv)
            this.gifs = gifs
        }

        override fun onClick(v: View?) {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}