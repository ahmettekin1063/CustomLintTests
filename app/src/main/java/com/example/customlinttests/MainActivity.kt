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

    fun bark(){
        Log.d(TAG, "woof! woof!")
        //illede bark
    }

    fun ahmet(){
        Log.e("illedebark", "illedebark")
        //before pr commit 1
        //after pr commit 2
        //after pr commit 3
        //before 1

    }

    companion object {
        private const val TAG = "Sample"
    }

}