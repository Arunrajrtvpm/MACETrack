package com.example.vaisakh.macetrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonCSE =findViewById<Button>(R.id.buttonCSE)
        buttonCSE.setOnClickListener{var i=Intent(this,ActivityCSE::class.java)
            startActivity(i)
        }



    }
}
