package com.example.e_fir

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class Superadmin_dashboard : AppCompatActivity() {

     lateinit var goVehAna:CardView
     lateinit var goPhoAna:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superadmin_dashboard)


        goPhoAna = findViewById(R.id.Phone_analysis_button)
        goVehAna = findViewById(R.id.Vehicle_analysis_button)


        goPhoAna.setOnClickListener {

            startActivity(Intent(this,Phone_analysis::class.java))
        }

        goVehAna.setOnClickListener {

            startActivity(Intent(this,Vehicle_analysis::class.java))
        }

        //val add_station=findViewById<ImageView>(R.id.button2)
        val add_station=findViewById<CardView>(R.id.button2)

        add_station.setOnClickListener{
            startActivity(Intent(this,Add_station_form::class.java))

        }



        val manage_station=findViewById<CardView>(R.id.button3)
        manage_station.setOnClickListener {
            startActivity(Intent(this,show_complaint_sadmin::class.java))
        }
    }

    //otion menue
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    //option menue commands and next activity
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        return when(item.itemId){
            R.id.logout->{
                FirebaseAuth.getInstance().signOut()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("LOGOUT")
                builder.setMessage("Do You want to logout from Applicatiom?")
                builder.setPositiveButton("Yes",{ dialogInterface : DialogInterface, i : Int ->
                    finish()
                    startActivity(Intent(this,Superadmin_login::class.java))})
                builder.setNegativeButton("No",{ dialogInterface : DialogInterface, i: Int ->})
                builder.show()
                true
            }
//            R.id.edit->{
//                startActivity(Intent(this, Edit_profile::class.java))
//                true
//            }
            else ->super.onOptionsItemSelected(item)

        }
    }

}