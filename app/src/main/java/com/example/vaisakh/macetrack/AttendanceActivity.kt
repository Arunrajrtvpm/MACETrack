package com.example.vaisakh.macetrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_attendance.*

class AttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        Take_attendance.setOnClickListener{
            startActivity(Intent(this,Attendance_taking::class.java))
        }



    }
}
