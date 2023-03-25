package com.example.e_fir

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class Phone_analysis : AppCompatActivity() {

    lateinit var radarChart2: RadarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_analysis)

        radarChart2= findViewById(R.id.Phone_analysis_chart)

        val list:ArrayList<RadarEntry> = ArrayList()

        list.add(RadarEntry(100f))
        list.add(RadarEntry(101f))
        list.add(RadarEntry(102f))
        list.add(RadarEntry(103f))
        list.add(RadarEntry(104f))

        val radarDataSet= RadarDataSet(list,"List")

        radarDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255)

        radarDataSet.lineWidth=2f

        radarDataSet.valueTextColor = Color.RED

        radarDataSet.valueTextSize=14f

        val radarData= RadarData()

        radarData.addDataSet(radarDataSet)

        radarChart2.data=radarData


        radarChart2.description.text= "Radar Chart"


        radarChart2.animateY(2000)
    }
}