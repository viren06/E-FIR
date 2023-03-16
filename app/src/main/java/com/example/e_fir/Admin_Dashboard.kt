package com.example.e_fir

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Admin_Dashboard : AppCompatActivity() {

    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    private lateinit var showcomplaintrv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        mauth= FirebaseAuth.getInstance()
        var user=mauth!!.currentUser
        var unm=user!!.uid
        var data= arrayListOf<showphonecomplaintModel>()
        showcomplaintrv=findViewById(R.id.rv_showcomplaint)

        var show_veh=findViewById<Button>(R.id.btn_miss_vehicle_show)
        show_veh.setOnClickListener {
            startActivity(Intent(this,show_vehicle_complaint::class.java))
        }


        val myref=database.getReference("phone")
        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                var ad=phonecomplaintAdapter(this@Admin_Dashboard,data)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(showphonecomplaintModel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            if (unm==value.sid){
                                data.add(value)
                            }

                        }
                    }
                    showcomplaintrv.adapter = ad
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        showcomplaintrv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }



    //otion menue
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    //option menue commands and next activity
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("LOGOUT")
                builder.setMessage("Do You want to logout from Applicatiom?")
                builder.setPositiveButton("Yes", { dialogInterface: DialogInterface, i: Int ->
                    finish()
                    startActivity(Intent(this, Superadmin_login::class.java))
                })
                builder.setNegativeButton("No", { dialogInterface: DialogInterface, i: Int -> })
                builder.show()
                true
            }
//            R.id.edit->{
//                startActivity(Intent(this, Edit_profile::class.java))
//                true
//            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}