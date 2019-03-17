package com.example.vaisakh.macetrack

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {



  /*  val username = usertext.text.toString()
    val password =pswdtext.text.toString()
    */
    override fun onCreate(savedInstanceState: Bundle?) {

      FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var button1 =findViewById<Button>(R.id.Button1)
        button1.setOnClickListener{
            login()
        }



        No_account.setOnClickListener{
            var i=Intent(this,Activity_signup::class.java)
            startActivity(i)}

    }

    private fun login(){
        val username = usertext.text.toString()
        val password =pswdtext.text.toString()

        //for checking the text field is empty
        if(username.isEmpty())
            usertext.error="Enter the user name"
        if(password.isEmpty())
             pswdtext.error="Enter the password"



        //Firebase authentication for creating user with email and password
        FirebaseApp.initializeApp(this)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Toast.makeText(this,"Sucessfully login",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, HomeActivity::class.java))

                }
                .addOnFailureListener{
                    Toast.makeText(this,"Failed to login",Toast.LENGTH_LONG).show()
                    Log.d("Main","Login failed : ${it.message}")
                }














    }


}
