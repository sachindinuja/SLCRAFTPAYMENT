package com.example.slcraft


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var Uinput1: EditText
    private lateinit var Uinput2: EditText
    private lateinit var Uinput3: EditText
    private lateinit var UinpuT4: EditText
    private lateinit var Bt1: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Uinput1 = findViewById(R.id.Uinput1)
        Uinput2 = findViewById(R.id.Uinput2)
        Uinput3 = findViewById(R.id.Uinput3)
        UinpuT4 = findViewById(R.id.UinpuT4)
        Bt1     = findViewById(R.id.Bt1)

        dbRef = FirebaseDatabase.getInstance().getReference("User")

        Bt1.setOnClickListener {
            saveUserData()
            nextActivity()
        }

    }



    private fun saveUserData() {

        //getting values
        val name = Uinput1.text.toString()
        val address = Uinput2.text.toString()
        val email = Uinput3.text.toString()
        val phone = UinpuT4.text.toString()

        if (name.isEmpty()) {
            Uinput1.error = "Required filed"
        }
        if (address.isEmpty()) {
            Uinput2.error = "Required filed"
        }
        if (email.isEmpty()) {
            Uinput3.error = "Required filed"
        }
        if (phone.isEmpty()) {
            UinpuT4.error = "Required filed"
        }

        val userId = dbRef.push().key!!

        val employee = UserData(userId, name, address, email, phone)

        dbRef.child(userId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                Uinput1.text.clear()
                Uinput2.text.clear()
                Uinput3.text.clear()
                UinpuT4.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }


        }

    private fun nextActivity()
    {
            val Intent = Intent(this, MainActivity2::class.java)
            startActivity(Intent)

    }

}






