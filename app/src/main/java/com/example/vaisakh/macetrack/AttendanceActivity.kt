package com.example.vaisakh.macetrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_attendance.*
import android.support.v7.app.AlertDialog;
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_dialog_with_edittext.*


class AttendanceActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)


            Take_attendance.setOnClickListener{

             //  Creating a Alert dialog box
                    val builder = AlertDialog.Builder(this)
                    val inflater = layoutInflater
                    builder.setTitle("Staff Login....")
                    val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
                    val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
                    builder.setView(dialogLayout)
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK") {
                        dialogInterface, i -> if(editText.text.toString() == "1234") {
                                                startActivity(Intent(this,Attendance_taking::class.java))
                                                  }

                                                else{
                                                    Toast.makeText(applicationContext, "Password Wrong", Toast.LENGTH_LONG).show()
                                                    }
                    }
                    builder.show()




            }



            View_attendance.setOnClickListener{
                startActivity(Intent(this, Attendance_view::class.java))
            }



    }
}
