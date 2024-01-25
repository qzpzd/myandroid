package com.example.daohang.ui.home

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daohang.databinding.FragmentHomeBinding
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val quotes = arrayOf(
        "The only way to do great work is to love what you do.",
        "Innovation distinguishes between a leader and a follower.",
        "Strive not to be a success, but rather to be of value.",
        "The future belongs to those who believe in the beauty of their dreams.",
        // Add more quotes here
    )
    private val randomIndex = Random.nextInt(quotes.size)

    private val _text = MutableLiveData<String>().apply {

//        value = quotes[randomIndex]
    }




    val text: LiveData<String> = _text

}