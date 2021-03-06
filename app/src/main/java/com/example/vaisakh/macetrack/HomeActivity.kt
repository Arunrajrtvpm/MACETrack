package com.example.vaisakh.macetrack

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        Track_button.setOnClickListener{

            //  Creating a Alert dialog box
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
       builder.setTitle("Principal Login....")
        val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setCancelable(false)
        builder.setPositiveButton("OK") {
            dialogInterface, i -> if(editText.text.toString() == "1234") {
            startActivity(Intent(this,MainActivity::class.java))
        }

        else{
            Toast.makeText(applicationContext, "Password Wrong", Toast.LENGTH_LONG).show()
        }
        }
        builder.show()

        }


        Attendance_button.setOnClickListener {
            startActivity(Intent(this,AttendanceActivity::class.java))
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Lets chat with friends", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            startActivity(Intent(this,MessengerActivity::class.java))
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.KTU -> {
                // Open the ktu page
                startActivity(Intent(this,KTUWebView::class.java))

                }


            R.id.KTU_portal -> {

            }
            R.id.MACE -> {

            }
            R.id.Impulse -> {

            }
            R.id.Google -> {

            }
            R.id.Placement -> {

            }


       }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
