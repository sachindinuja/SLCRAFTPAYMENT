package com.example.slcraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slcraft.R.layout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {

        private lateinit var Uinput1p3: EditText
        private lateinit var Uinput2p3: EditText
        private lateinit var Uinput3p3: EditText
        private lateinit var Uinput4p3: EditText
        private lateinit var Bt1p3: Button
        private lateinit var Bt2p3: Button

        private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main3)

        Uinput1p3 = findViewById(R.id.Uinput1p3)
        Uinput2p3 = findViewById(R.id.Uinput2p3)
        Uinput3p3 = findViewById(R.id.Uinput3p3)
        Uinput4p3 = findViewById(R.id.Uinput4p3)
        Bt1p3     = findViewById(R.id.Bt1p3)
        Bt2p3     =  findViewById(R.id.Bt2p3)

        dbRef = FirebaseDatabase.getInstance().getReference("CardDetails")

        Bt2p3.setOnClickListener {
            saveUserCData()
            nextActivity4()
        }

    }
        private fun saveUserCData() {

            //getting values
            val Cnumber = Uinput1p3.text.toString()
            val Emonth = Uinput2p3.text.toString()
            val Eyear = Uinput3p3.text.toString()
            val Cvc = Uinput4p3.text.toString()



            if (Cnumber.isEmpty()) {
                Uinput1p3.error = "Required filed"
            }
            if (Emonth.isEmpty()) {
                Uinput2p3.error = "Required filed"
            }
            if (Eyear.isEmpty()) {
                Uinput3p3.error = "Required filed"
            }
            if (Cvc.isEmpty()) {
                Uinput4p3.error = "Required filed"
            }

            val userId = dbRef.push().key!!

            val employee = CardDetails(userId, Cnumber, Emonth, Eyear, Cvc)

            dbRef.child(userId).setValue(employee)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    Uinput1p3.text.clear()
                    Uinput2p3.text.clear()
                    Uinput3p3.text.clear()
                    Uinput4p3.text.clear()


                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }


        }

        private fun nextActivity4() {
            val Intent = Intent(this, MainActivity4::class.java)
            startActivity(Intent)
        }


}



