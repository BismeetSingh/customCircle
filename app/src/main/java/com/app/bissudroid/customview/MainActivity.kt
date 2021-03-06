package com.app.bissudroid.customview

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        happyButton.setOnClickListener {
            emotionalFaceView.happinessState=EmotionalfaceView.HAPPY
        }
        sadButton.setOnClickListener {
            emotionalFaceView.happinessState=EmotionalfaceView.SAD
        }
    }


}
