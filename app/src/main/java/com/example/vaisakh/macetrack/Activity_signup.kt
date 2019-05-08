package com.example.vaisakh.macetrack

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class Activity_signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var buttonReg =findViewById<Button>(R.id.Register)
        buttonReg.setOnClickListener {
           CreateAccount()
        }

        //Select Photo button working
        select_photo.setOnClickListener{
            Log.d("signup", "try to show photo selector")

            //code for selecting photo
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)

        }

    }

    //Photo selection and displaying
    var selectedPhotoUri : Uri? =null          //to acces the value later declare variable here
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)
        {
            //check what the selected photo
            Log.d("signup", "photo selected")

           selectedPhotoUri=data.data
            val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)

            select_photo_frame.setImageBitmap(bitmap)
            select_photo.alpha=0f
            // val bitmapDrawable=BitmapDrawable(bitmap)
            //select_photo.setBackgroundDrawable(bitmapDrawable)

        }
    }



    private fun CreateAccount(){
        val Email = email.text.toString()
        val Password = password.text.toString()

        if (Email.isEmpty()){
            email.error="Enter email"
        }
        if (Password.isEmpty()){
            password.error="Enter email"
        }
        if(Email.isEmpty() && Password.isEmpty())
        {
            Toast.makeText(this,"Enter values to the field",Toast.LENGTH_LONG).show()

        }

        //Firebase authentication for creating user with email and password
        FirebaseApp.initializeApp(this)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Toast.makeText(this,"Sucessfully Registerd",Toast.LENGTH_LONG).show()
                   // uploadImage()       //method for uploading image

                }
                .addOnFailureListener{
                    Toast.makeText(this,"Failed to create account",Toast.LENGTH_LONG).show()
                    Log.d("signup","Creation failed : ${it.message}")
                }


        //Activity switching
        var i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }


    //method implementation for image upload
    private fun uploadImage(){

        if (selectedPhotoUri == null) return
        val filename =UUID.randomUUID().toString()              //to create unique id, use UUID
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename") //Storing images to a directory with file name

            //ref is reference for firebase
        ref.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {
                    Log.d("signup","successfully uploaded image : ${it.metadata?.path}")

                    //to access the file
                    ref.downloadUrl.addOnSuccessListener {

                        Log.d("signup","File location : $it")

                        saveUserToDatabase(it.toString())  //passing url of image to functin
                        //saveUserToDatabase()  //passing url of image to functin

                    }

                }
                .addOnFailureListener{
                    Log.d("signup", "failed")
                }
    }

    //method for saving data to firebase

    private fun saveUserToDatabase(profileImage: String){

        val uid=FirebaseAuth.getInstance().uid ?: ""
        val ref=FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid,username.text.toString(), profileImage)
        //val user = User(uid,username.text.toString())

        ref.setValue(user)
                .addOnSuccessListener {
                    Log.d("signup", "Saved user to firebase")
                }
                .addOnFailureListener{
                    Log.d("signup", "failed Saving user to firebase")
                }
    }
    }

class  User(val uid:String, val username: String, val profileImage:String)

//class  User(val uid:String, val username: String)