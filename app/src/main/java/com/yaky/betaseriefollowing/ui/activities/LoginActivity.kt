package com.yaky.betaseriefollowing.ui.activities

import android.os.Bundle
import com.yaky.betaseriefollowing.R
import org.jetbrains.anko.AnkoLogger

class LoginActivity: BaseActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }
}