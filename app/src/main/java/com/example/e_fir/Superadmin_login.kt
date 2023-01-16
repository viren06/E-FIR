package com.example.e_fir

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
            startActivity(Intent(this@Superadmin_login,Admin_Dashboard::class.java))
            finish()
        }


        login.setOnClickListener{

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("policestation")


            mAuth!!.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (email!!.text.toString() == "admin" && password!!.text.toString() == "admin") {
                        startActivity(Intent(this,Superadmin_dashboard::class.java))
                        Toast.makeText(applicationContext, "Redirecting to super admin dashboard", Toast.LENGTH_SHORT).show()
                    }
                    else if (task.isSuccessful) {
                        mAuth=FirebaseAuth.getInstance()
                        val user = mAuth!!.currentUser
                        if (user != null) {
                            myRef.child(user!!.uid).addListenerForSingleValueEvent(object :ValueEventListener{
                                override fun onCancelled(p0: DatabaseError) {
                                    TODO("Not yet implemented")
                                }
                                override fun onDataChange(p0: DataSnapshot) {
                                    var value=p0.getValue(HashMap::class.java)
                                    myRef.child(user.uid).setValue(value)
                                }
                            })
                        }

                        startActivity(Intent(this@Superadmin_login,Admin_Dashboard::class.java))

                    }
                    else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        // updateUI(null)
                        // ...
                    }

                    }


//            if (user_name!!.text.toString() == "admin" && password!!.text.toString() == "admin") {
//                startActivity(Intent(this,Superadmin_dashboard::class.java))
//                Toast.makeText(applicationContext, "Redirecting...", Toast.LENGTH_SHORT).show()
//            }
//            else {
//                Toast.makeText(applicationContext, "Wrong Credentials", Toast.LENGTH_SHORT).show()
//                login!!.visibility = View.VISIBLE
//                login!!.setBackgroundColor(Color.RED)
//                counter--
//                login!!.text = Integer.toString(counter)
//                if (counter == 0) {
//                    login!!.isEnabled = false
//                    login!!.visibility = View.GONE
//                }
//            }
        }
    }


}