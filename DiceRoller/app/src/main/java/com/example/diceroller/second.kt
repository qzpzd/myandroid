package com.example.diceroller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class second  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)

        val keyValue1 = intent.getStringExtra("key1")
        val keyValue2 = intent.getDoubleExtra("key2",0.0)

        Log.i("AnotherActivity", "keyValue1 is "+keyValue1)
        Log.i("AnotherActivity", "keyValue2 is "+keyValue2)
    }
}
