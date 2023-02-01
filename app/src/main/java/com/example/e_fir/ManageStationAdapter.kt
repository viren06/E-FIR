package com.example.e_fir

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class ManageStationAdapter (var ctx: Activity, var arlist: ArrayList<stationmodel>): RecyclerView.Adapter<ManageStationAdapter.viewholder>() {
    val database = FirebaseDatabase.getInstance()

    inner class viewholder(v: View):RecyclerView.ViewHolder(v){
        var station_name : TextView = v.findViewById(R.id.Station_name)
        var Station_email : TextView = v.findViewById(R.id.Station_Email)
        var edit : Button =v.findViewById(R.id.stationedit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.manage_station_view,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.station_name.text=arlist[position].username
        holder.Station_email.text=arlist[position].email

        holder.edit.setOnClickListener {
            var int1= Intent(ctx,editStationDetails::class.java)
            ctx.startActivity(int1)
        }
    }

    override fun getItemCount(): Int {
        return arlist.size
    }
}