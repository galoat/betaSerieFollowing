package com.yaky.betaseriefollowing.ui.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.yaky.betaseriefollowing.R
import kotlinx.android.synthetic.main.base_layout.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


abstract class BaseActivity : AppCompatActivity(),  MenuItem.OnMenuItemClickListener, AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.base_layout)
    }

    override fun setContentView(layoutResID: Int) {
        if (view_stub != null) {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val lp = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            val stubView = inflater.inflate(layoutResID, view_stub, false)
            view_stub.addView(stubView, lp)
            val drawerMenu = navigation_view.getMenu()
            for (i in 0 until drawerMenu.size()) {
                drawerMenu.getItem(i).setOnMenuItemClickListener(this)
            }
        }
    }

    override fun setContentView(view: View) {
        if (view_stub != null) {
            val lp = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            view_stub.addView(view, lp)
        }
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        if (view_stub != null) {
            view_stub.addView(view, params)
        }
    }


    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.login -> {
                info{
                    "login"
                }
            }
            R.id.listSeries -> {
                info{
                    "list series"
                }
            }

        }
        return false
    }

}