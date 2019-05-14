package com.example.vaisakh.macetrack

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.SubMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_attendance_view.*
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

class Attendance_view : AppCompatActivity() {


    lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_view)




        var branch =findViewById<Spinner>(R.id.Spinner_Branch)
        var sem =findViewById<Spinner>(R.id.Spinner_Class)
        var rl_no =findViewById<Spinner>(R.id.Spinner_Rollnum)
        var b:String=""
        var s:String=""
        var r:String=""

        Log.d("Branch","Branch Spinner started")
        //Spinner for Branches
        val branches = arrayOf("Computer Science","Civil","Electronics","Electrical","Mechanical")
        branch.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,branches)
        branch.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            Toast.makeText(applicationContext, "Please Select A value", Toast.LENGTH_LONG).show()

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Toast.makeText(applicationContext,"You Selected  :${branches.get(position)}",Toast.LENGTH_LONG).show()
            b=branches.get(position)
                        }

        }

        //Spinner for class
        val sems = arrayOf("S1","S2","S3","S4","S5","S6","S7","S8")
        sem.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,sems)
        sem.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Please Select A value", Toast.LENGTH_LONG).show()

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext,"You Selected  :${sems.get(position)}",Toast.LENGTH_LONG).show()
                s=sems.get(position)
            }

        }


        //Spinner for Rollnum
        val rl_nos = arrayOf("1","2","3","4","5")
        rl_no.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,rl_nos)
        rl_no.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Please Select A value", Toast.LENGTH_LONG).show()

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(applicationContext,"You Selected  :${rl_nos.get(position)}",Toast.LENGTH_LONG).show()
                r=rl_nos.get(position)
            }

        }

        //simple date format
        val sdf = SimpleDateFormat("MM/dd ")
        val currentDate = sdf.format(Date())
        Log.d("Date", "Date is $currentDate")
        //Date picker button
        Button_Date.setOnClickListener{
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                Toast.makeText(applicationContext,"Year :"+year+"\nMonth  :"+month+"\nDay   :"+dayOfMonth,Toast.LENGTH_LONG).show()

            },
                    now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()


        }

        Submit.setOnClickListener{

            val status:String="Present"
            val state:Int=5
            val name:String="Arun"

            //Code for retriving data from firebase
            val ref= FirebaseDatabase.getInstance().getReference().child("Attendance_marking").child("1")
            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(applicationContext,"not retrived",Toast.LENGTH_LONG).show()
                    Log.d("retrival","retrival failed")
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    Toast.makeText(applicationContext,"Data retrived",Toast.LENGTH_LONG).show()
                    Log.d("retrival","retrival Successfull")
                    val name=p0.child("name").getValue().toString()
                    if (p0!!.exists()){
                        for(h in p0.children){
                           // val status = h.getValue(SaveAttendance::class.java)
                           // val state= h.getValue(SaveAttendance::class.java)
                            val status =p0.child("status").getValue().toString()
                        }
                    }
                }

            })



            val builder = AlertDialog.Builder(this)
            builder.setTitle("Your Attendance Status")
            builder.setMessage("Branch    :$b \n" +
                    "Class    :$s \n" +
                    "Roll.No    :$r \n" +
                    "\t"+status+":"+name+""+state+" ..............")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(applicationContext,
                        "Thank You", Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }



    }
}

