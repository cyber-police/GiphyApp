package com.example.giphyapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class GiphyRecyclerViewAdapter(
    private val context: Context,
    private val gifs: List<DataObject>,
    private val onItemListener: OnItemClickListener?
) : RecyclerView.Adapter<GiphyRecyclerViewAdapter.GiphyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiphyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.gif_layout, parent, false)
        return GiphyViewHolder(view, gifs, onItemListener)
    }

    override fun onBindViewHolder(holder: GiphyViewHolder, position: Int) {
        val data = gifs[position]
        Glide.with(context).load(data.images.originalImage.url).into(holder.gifImageView)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    interface OnItemClickListener {
        fun onItemClick(value: String)
    }

    class GiphyViewHolder(
        itemView: View,
        gifs: List<DataObject>,
        onItemListener: OnItemClickListener?
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var onItemListener: OnItemClickListener? = null
        private val gifs: List<DataObject>
        var gifImageView: ImageView

        init {
            gifImageView = itemView.findViewById(R.id.gif_iv)
            this.onItemListener = onItemListener
            this.gifs = gifs
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onItemListener!!.onItemClick(
                gifs[adapterPosition].images.originalImage.url
            )
        }
    }
}