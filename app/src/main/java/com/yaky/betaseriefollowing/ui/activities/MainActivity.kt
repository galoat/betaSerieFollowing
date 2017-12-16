package com.yaky.betaseriefollowing.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yaky.betaseriefollowing.R
import org.jetbrains.anko.AnkoLogger

class MainActivity : AppCompatActivity(), AnkoLogger {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


