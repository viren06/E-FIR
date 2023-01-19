package com.example.e_fir

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Superadmin_login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_login)

        mAuth=FirebaseAuth.getInstance()
        val user = mAuth!!.currentUser
        var email=findViewById<EditText>(R.id.textView1)
        var password=findViewById<EditText>(R.id.textView2)
        var counter=3
        val login=findViewById<Button>(R.id.button)

        if(user != null){
            updateUi(user)
        }


        login.setOnClickListener{

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("policestation")
            var progressDialog = ProgressDialog(this)
            progressDialog.setTitle("It will take some time")
            progressDialog.setMessage("hello")
            progressDialog.show()


            mAuth!!.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        mAuth=FirebaseAuth.getInstance()
                       val user = mAuth!!.currentUser
                        updateUi(user)
                        progressDialog.dismiss()
                    }
                    else if (email!!.text.toString() == "admin" && password!!.text.toString() == "admin") {
                       startActivity(Intent(this,Superadmin_dashboard::class.java))
                       Toast.makeText(applicationContext, "Redirecting to super admin dashboard", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                    }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        if(user!!.isEmailVerified){
            startActivity(Intent(this,Admin_Dashboard::class.java))
            finish()
        }
        else{
            user.sendEmailVerification()
            Toast.makeText(this,"Verify email first",Toast.LENGTH_LONG).show()
        }

    }


}