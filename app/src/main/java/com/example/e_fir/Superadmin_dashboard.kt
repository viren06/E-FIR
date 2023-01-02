package com.example.e_fir

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Superadmin_dashboard : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_dashboard)

        val add_station=findViewById<Button>(R.id.button2)

        add_station.setOnClickListener{
            startActivity(Intent(this,Add_station_form::class.java))

        }
    }
}