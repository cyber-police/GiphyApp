package com.example.giphyapp.feature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.giphyapp.databinding.ActivityChosenGifBinding

class ChosenGifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChosenGifBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChosenGifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("gif")).into(binding.chosenGifIv)
    }
}