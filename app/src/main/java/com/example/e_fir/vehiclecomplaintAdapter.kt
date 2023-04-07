package com.example.e_fir

import android.app.Activity
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class vehiclecomplaintAdapter  (var ctx: Activity, var arlist: ArrayList<showvehiclecomplaintModel>): RecyclerView.Adapter<vehiclecomplaintAdapter.viewholder>()  {
    val database = FirebaseDatabase.getInstance()

    inner class viewholder(v: View):RecyclerView.ViewHolder(v){
        var user_name : TextView = v.findViewById(R.id.txt_name)
        var aadhar_number : TextView = v.findViewById(R.id.txt_aadharnumber)
        var address : TextView = v.findViewById(R.id.txt_address)
        var user_mail : TextView =v.findViewById(R.id.txt_email)
        var register_num : TextView =v.findViewById(R.id.txt_registration_number)
        var rc_num : TextView =v.findViewById(R.id.txt_rcnumber)
        var relation_with_usr : TextView =v.findViewById(R.id.txt_relation)
        var vdetails : TextView =v.findViewById(R.id.vehicle_type_model)
        var lastSdate : TextView =v.findViewById(R.id.txt_lastdate)
        var sold : Button =v.findViewById(R.id.btn_status)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.missing_vehicle_show,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.user_name.text=arlist[position].name
        holder.aadhar_number.text=arlist[position].aadhar_number
        holder.address.text=arlist[position].address
        holder.user_mail.text=arlist[position].email
        holder.register_num.text=arlist[position].registration_number
        holder.rc_num.text=arlist[position].other_details
        holder.relation_with_usr.text=arlist[position].relation_with_user
        holder.vdetails.text=arlist[position].vehicle_type
        holder.lastSdate.text=arlist[position].last_seen_date

        var vehicle=arlist[position]

        if(arlist[position].status=="solved"){
            holder.sold.isEnabled=false
        }
        else
        {
            holder.sold.isEnabled=true
        }
        vehicle.status="processing"

        holder.sold.setOnClickListener {
            vehicle.status="solved"

            val builder = AlertDialog.Builder(ctx)
            builder.setTitle("SOLVED")
            builder.setMessage("Are you sure about the solveing of complaint?")
            builder.setPositiveButton("Yes",{ dialogInterface : DialogInterface, i : Int ->
                val myref=database.getReference("vehicle")
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val formatted = current.format(formatter)
                vehicle.stime=formatted
                myref.child(arlist[position].vehicleid.toString()).setValue(vehicle)
            })
            builder.setNegativeButton("No",{ dialogInterface : DialogInterface, i: Int ->})
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return arlist.size
    }

}