package com.example.customlinttests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ahmet()
    }

    fun cat() {
        Log.d(TAG, "meow! meow!")
        //nobark
    }

    fun ahmet() {
        Log.e("meow", "meow")
    }

    companion object {
        private const val TAG = "Sample"
    }

}