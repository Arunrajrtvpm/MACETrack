package com.example.vaisakh.macetrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ActivityCSE : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cse)

        var buttontstaff=findViewById<Button>(R.id.buttontstaff)
        buttontstaff.setOnClickListener{var i=Intent(this,ScrollingActivityCSE::class.java)
        startActivity(i)
        }





    }
}
