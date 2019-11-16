package com.basyony.convertnow.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.basyony.convertnow.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLoading(){
        progress_bar.show()
    }

    fun hideLoading(){
        progress_bar.hide()
    }
}