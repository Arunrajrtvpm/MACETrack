package com.example.vaisakh.macetrack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_staff__cse.*

class Staff_CSEActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff__cse)

        //Button A-Aswathi Maam
        Button_a.setOnClickListener{
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Staff Location")
        builder.setMessage("The Staff is in .........Class")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton("Ok") { dialog, which ->
            Toast.makeText(applicationContext,
                    "Located", Toast.LENGTH_SHORT).show()
        }
        /*  These are other buttons for , negative option (cansel) and maybe option
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Maybe") { dialog, which ->
            Toast.makeText(applicationContext,
                    "Maybe", Toast.LENGTH_SHORT).show()
        }*/
        builder.show()
        }

    }
}
