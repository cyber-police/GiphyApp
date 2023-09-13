package com.example.giphyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), GiphyRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var gifViewModel: GifViewModel

    @Inject
    lateinit var gifViewModelFactory: GifViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.giphyRv.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        gifViewModel = ViewModelProvider(this, gifViewModelFactory)[GifViewModel::class.java]

        val gifs = mutableListOf<DataObject>()
        val adapter = GiphyRecyclerViewAdapter(this, gifs, this)
        binding.giphyRv.adapter = adapter

        gifViewModel.dataResult.observe(this) {
            when (it) {
                is DataResult.Success -> {
                    gifs.addAll(it.data)
                    adapter.notifyDataSetChanged()
                }

                is DataResult.Loading -> {

                }

                is DataResult.Error -> {

                }
            }
        }
        gifViewModel.getGifs()
    }

    override fun onItemClick(gifs: String) {

    }
}