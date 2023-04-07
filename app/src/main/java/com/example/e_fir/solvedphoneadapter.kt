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


class solvedphoneadapter (var ctx: Activity, var arlist: ArrayList<showphonecomplaintModel>): RecyclerView.Adapter<solvedphoneadapter.viewholder>() {
    val database = FirebaseDatabase.getInstance()

    inner class viewholder(v: View): RecyclerView.ViewHolder(v){
        var user_name : TextView = v.findViewById(R.id.txt_name)
        var aadhar_number : TextView = v.findViewById(R.id.txt_aadharnumber)
        var address : TextView = v.findViewById(R.id.txt_address)
        var user_mail : TextView =v.findViewById(R.id.txt_email)
        var mobile_num : TextView =v.findViewById(R.id.txt_registration_number)
        var serial_num : TextView =v.findViewById(R.id.txt_rcnumber)
        var relation_with_usr : TextView =v.findViewById(R.id.txt_relation)
        var mdetails : TextView =v.findViewById(R.id.txt_details)
        var lastSdate : TextView =v.findViewById(R.id.txt_lastdate)
        var  sdate :TextView =v.findViewById(R.id.txt_sdate)
        //val uuid:String?=null


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val v=ctx.layoutInflater.inflate(R.layout.solved_phone_view,parent,false)
        return  viewholder(v)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.user_name.text=arlist[position].name
        holder.aadhar_number.text=arlist[position].aadhar_number
        holder.address.text=arlist[position].address
        holder.user_mail.text=arlist[position].email
        holder.mobile_num.text=arlist[position].mobile_number
        holder.serial_num.text=arlist[position].serial_number
        holder.relation_with_usr.text=arlist[position].relation_with_user
        holder.mdetails.text=arlist[position].mobile_details
        holder.lastSdate.text=arlist[position].last_seen_date
        //holder.sdate.text=arlist[position].stime
        //holder.uuid.toString()

//        var phone=arlist[position]
//
//        if(arlist[position].status=="solved"){
//            holder.sold.isEnabled=false
//        }
//        else
//        {
//            holder.sold.isEnabled=true
//        }
//        phone.status="processing"
//
//        holder.sold.setOnClickListener {
//            phone.status="solved"
//            val builder = AlertDialog.Builder(ctx)
//            builder.setTitle("SOLVED")
//            builder.setMessage("Are you sure about the solveing of complaint?")
//            builder.setPositiveButton("Yes",{ dialogInterface : DialogInterface, i : Int ->
//                val myref=database.getReference("phone")
//                val current = LocalDateTime.now()
//                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//                val formatted = current.format(formatter)
//                phone.stime=formatted
//                myref.child(arlist[position].phoneid.toString()).setValue(phone)
//            })
//            builder.setNegativeButton("No",{ dialogInterface : DialogInterface, i: Int ->})
//            builder.show()
//        }
    }

    override fun getItemCount(): Int {
        return arlist.size
    }
}