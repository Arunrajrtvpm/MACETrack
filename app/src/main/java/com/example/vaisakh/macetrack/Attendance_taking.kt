package com.example.vaisakh.macetrack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class Attendance_taking : AppCompatActivity() {
    lateinit var RollNo:String
    lateinit var Name:String
    var Attendance:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_taking)

        RollNo=findViewById<TextView>(R.id.RollNo1).text.toString()
        Name = findViewById<TextView>(R.id.Name1).text.toString()
       var toggleButton=findViewById<ToggleButton>(R.id.toggleButton1)
        toggleButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            Attendance = if (isChecked) 1 else 0
            Log.d("Attendance", "Toggle button action$Attendance")

        }

        var Submit=findViewById<Button>(R.id.Submit_button)

        Submit.setOnClickListener{
            saveData()
        }


            }


    private fun saveData(){

    //Code for getting the date
        val sdf = SimpleDateFormat("MM/dd ")
        val currentDate = sdf.format(Date())
        Log.d("Date", "Date is $currentDate")
       // Toast.makeText(applicationContext,"Date is $currentDate",Toast.LENGTH_LONG).show()


        val ref=FirebaseDatabase.getInstance().getReference("Attendance_marking-$currentDate")
        val Student_id=ref.push().key       //it will generate an unique id incide the database

        val obj_attendance=SaveAttendance(RollNo,Name,Attendance)       //object of SaveAttendance class with the values

        // val obj_attendance=SaveAttendance(RollNo,Student_id.toString(),Name,Attendance)       //object of SaveAttendance class with the values

        //ref.child(Student_id.toString()).setValue(obj_attendance).addOnCompleteListener{

        ref.child(RollNo.toString()).setValue(obj_attendance).addOnCompleteListener{
            Log.d("Attendance", "Adding attendance to firebase database")

            Toast.makeText(applicationContext,"Successfully Marked Attendance",Toast.LENGTH_LONG).show()

        }


    }


}
