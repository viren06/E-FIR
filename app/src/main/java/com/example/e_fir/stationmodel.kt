package com.example.e_fir

import com.google.android.gms.common.api.Status

data class stationmodel(var email:String?=null, var username:String?=null,var district:String?=null , var status:String?=null ){
    constructor():this("","","","")
}
