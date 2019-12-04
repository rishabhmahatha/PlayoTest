package com.app.playotest.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.app.playotest.R
import com.app.playotest.fragment.HomeFragment
import com.app.playotest.utils.Utility

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Utility.replaceFragment(R.id.activity_flContainer,supportFragmentManager,HomeFragment(),false)
    }
}