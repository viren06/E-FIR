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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class show_vehicle_complaint : AppCompatActivity() {

    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    private lateinit var showvehiclecomplaintrv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_vehicle_complaint)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)


        mauth= FirebaseAuth.getInstance()
        var user=mauth!!.currentUser
        var unm=user!!.uid
        var data= arrayListOf<showvehiclecomplaintModel>()
        showvehiclecomplaintrv=findViewById(R.id.rv_show_vehicle)

        val myref=database.getReference("vehicle")
        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                var ad=vehiclecomplaintAdapter(this@show_vehicle_complaint,data)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(showvehiclecomplaintModel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                            if (unm==value.sid && value.status=="processing"){
                                data.add(value)
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