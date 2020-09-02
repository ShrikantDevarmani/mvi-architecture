package com.logicsoulllp.classtest.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.logicsoulllp.classtest.R

/**
 * Main activity todo change accordingly
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun startAct(context: Context) {
            val mainActivity = Intent(context, MainActivity::class.java)
            context.startActivity(mainActivity)
        }
    }
}