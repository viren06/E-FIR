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

class Manage_station : AppCompatActivity() {

    val database= FirebaseDatabase.getInstance()
    private var mauth: FirebaseAuth?=null
    private lateinit var ManageStationRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_station)

            //mauth= FirebaseAuth.getInstance();
        //var user=mauth!!.currentUser
        var data= arrayListOf<stationmodel>()
        ManageStationRecyclerView = findViewById(R.id.managestation_rv)

        val myref=database.getReference("policestation")
        myref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                data.clear()
                var ad= ManageStationAdapter(this@Manage_station,data)
                if(datasnapshot.exists()) {
                    for (v in datasnapshot.children) {
                        val value = v.getValue(stationmodel::class.java)
                        Log.d("key", value.toString())
                        if (value != null) {
                                data.add(value)
                        }
                    }
                    ManageStationRecyclerView.adapter = ad
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        ManageStationRecyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)


    }
}