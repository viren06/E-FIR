package com.example.e_fir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Solved_complaint : AppCompatActivity() {

    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    private lateinit var showcomplaintrv: RecyclerView
    private lateinit var showvehiclecomplaintrv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solved_complaint)

        mauth= FirebaseAuth.getInstance()
        var user=mauth!!.currentUser
        var unm=user!!.uid
        var data= arrayListOf<showphonecomplaintModel>()
        showcomplaintrv=findViewById(R.id.rv_phone_solved)

        val myref=database.getReference("phone")
        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                var ad=phonecomplaintAdapter(this@Solved_complaint,data)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(showphonecomplaintModel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            if (unm==value.sid && value.status=="solved"){
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

        var data1= arrayListOf<showvehiclecomplaintModel>()
        showvehiclecomplaintrv=findViewById(R.id.rv_vehicle_solved)


        val myRef=database.getReference("vehicle")
        myRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data1.clear()
                var ad=vehiclecomplaintAdapter(this@Solved_complaint,data1)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(showvehiclecomplaintModel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            if (unm==value.sid && value.status=="solved"){
                                data1.add(value)
                            }

                        }
                    }
                    showvehiclecomplaintrv.adapter = ad
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        showvehiclecomplaintrv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

    }
}