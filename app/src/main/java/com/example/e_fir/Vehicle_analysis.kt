package com.example.e_fir

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class Vehicle_analysis : AppCompatActivity() {

    lateinit var piechart2: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_analysis)

        piechart2= findViewById(R.id.Vehicle_analysis_chart)


        val list:ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(180f,"Vadodara"))
        list.add(PieEntry(20f,"Ahmedabad"))
        list.add(PieEntry(83f,"Surat"))
        list.add(PieEntry(245f,"Valsad"))
        list.add(PieEntry(132f,"Gandhinagar"))

        val pieDataSet= PieDataSet(list,"Report")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextColor= Color.BLACK
        pieDataSet.valueTextSize=15f

        val pieData= PieData(pieDataSet)

        piechart2.data= pieData

        piechart2.description.text= "Analysis of Missing Vehicle Complaints"

        piechart2.centerText="Analysis of Missing Vehicle Complaints"

        piechart2.animateY(2000)


    }
}