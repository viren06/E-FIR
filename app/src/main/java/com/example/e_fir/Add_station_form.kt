package com.example.e_fir

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Add_station_form : AppCompatActivity() {

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    //private var username:String?=null

    private var flg:String="false"
    private var mAuth: FirebaseAuth? = null
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var username:EditText
    lateinit var district:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_station_form)

        mAuth = FirebaseAuth.getInstance();
        var isAllFieldsChecked = false

        val registration =findViewById<Button>(R.id.btnreg)
         email=findViewById<EditText>(R.id.txtemail)
         username=findViewById<EditText>(R.id.txtusernm)
         password=findViewById<EditText>(R.id.txtpass)
         district=findViewById<Spinner>(R.id.spinner)

        registration.setOnClickListener{
            //check password
            var userfirebase: FirebaseUser?=null
            isAllFieldsChecked = checkAllFields()

            var progressDialog= ProgressDialog(this)
            progressDialog.setTitle("It will take some time")
            progressDialog.setMessage("Signing Up")
            progressDialog.show()

            when{
                TextUtils.isEmpty(email.text.toString())->email.error="Required"
                TextUtils.isEmpty(password.text.toString())-> Toast.makeText(this,"Enter password", Toast.LENGTH_LONG).show()
                TextUtils.isEmpty(username.text.toString())->Toast.makeText(this,"Enter username", Toast.LENGTH_LONG).show()

                else->{
                    mAuth!!.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                        .addOnCompleteListener(this){task ->
                            val user = mAuth!!.currentUser
                            if (user != null){
                                userfirebase= user

                                var hashMap=HashMap<String,Any>()
                                //firebase data entry
                                hashMap["username"]=username.text.toString()
                                hashMap["email"]=email.text.toString()
                                hashMap["password"]=password.text.toString()
                                hashMap["district"]=district.selectedItem.toString()
                                hashMap["status"]="Open"

                                var myref= FirebaseDatabase.getInstance().getReference("policestation")
                                myref.child(user!!.uid.toString()).setValue(hashMap).addOnCompleteListener(this){
                                    Toast.makeText(this,"user created",Toast.LENGTH_SHORT).show()
                                    progressDialog.dismiss()

                                    var int1= Intent(this@Add_station_form, Superadmin_dashboard::class.java)
                                    int1.putExtra("username",username.toString())
                                    int1.putExtra("password",password.toString())
                                    startActivity(int1)
                                    finish()
                                }
                            }
                        }
                        .addOnCanceledListener {
                            Toast.makeText(this,"error try after some time",Toast.LENGTH_LONG).show()
                            progressDialog.dismiss()
                        }
                }
            }
            progressDialog.dismiss()
        }

    }

    private fun checkAllFields(): Boolean {
        var password1=findViewById<EditText>(R.id.txtpass)
        if (password1.length()<8){
            password1.setError("password must be minimum 8 characters")
        }
        return true

    }
}