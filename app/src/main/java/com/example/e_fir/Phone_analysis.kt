package com.example.e_fir

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class Phone_analysis : AppCompatActivity() {

    lateinit var pieChart: PieChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_analysis)

        pieChart = findViewById(R.id.Phone_analysis_chart)

        val list:ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(80f,"Vadodara"))
        list.add(PieEntry(200f,"Ahmedabad"))
        list.add(PieEntry(183f,"Surat"))
        list.add(PieEntry(245f,"Valsad"))
        list.add(PieEntry(116f,"Gandhinagar"))

        val pieDataSet= PieDataSet(list,"Report")

        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)
        pieDataSet.valueTextColor= Color.BLACK
        pieDataSet.valueTextSize=15f

        val pieData= PieData(pieDataSet)

        pieChart.data= pieData

        pieChart.description.text= "Analysis of Missing Phone Complaints"

        pieChart.centerText="Analysis of Missing Phone Complaints"

        pieChart.animateY(2000)
    }
}