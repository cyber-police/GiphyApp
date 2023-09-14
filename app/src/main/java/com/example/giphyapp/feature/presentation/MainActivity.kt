package com.example.giphyapp.feature.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giphyapp.feature.presentation.ui.GifViewModel
import com.example.giphyapp.feature.presentation.ui.GifViewModelFactory
import com.example.giphyapp.feature.presentation.ui.GiphyRecyclerViewAdapter
import com.example.giphyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
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

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.giphyRv.layoutManager = layoutManager

        gifViewModel = ViewModelProvider(this, gifViewModelFactory)[GifViewModel::class.java]

        val adapter = GiphyRecyclerViewAdapter(this@MainActivity)
        binding.giphyRv.adapter = adapter

        lifecycleScope.launch {
            gifViewModel.getGifs().collectLatest { gifs ->
                adapter.submitData(gifs)
            }
        }
    }

    override fun onItemClick(gif: String) {
        val intent = Intent(this@MainActivity, ChosenGifActivity::class.java)
        intent.putExtra("gif", gif)
        startActivity(intent)
    }
}