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

class Superadmin_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_login)

        var user_name=findViewById<EditText>(R.id.textView1)
        var password=findViewById<EditText>(R.id.textView2)
        var counter=3
        val login=findViewById<Button>(R.id.button)
        //login!!.visibility = View.GONE


        login.setOnClickListener{
            if (user_name!!.text.toString() == "admin" && password!!.text.toString() == "admin") {
                startActivity(Intent(this,Add_station::class.java))
                Toast.makeText(applicationContext, "Redirecting...", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                login!!.visibility = View.VISIBLE
                login!!.setBackgroundColor(Color.RED)
                counter--
                login!!.text = Integer.toString(counter)
                if (counter == 0) {
                    login!!.isEnabled = false
                    login!!.visibility = View.GONE

                }
            }
        }
    }
}