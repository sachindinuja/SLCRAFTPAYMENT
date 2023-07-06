package com.example.slcraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {


    private lateinit var Uinput1: EditText
    private lateinit var Uinput2: EditText
    private lateinit var Uinput3: EditText
    private lateinit var Uinput4: EditText
    private lateinit var myRef: DatabaseReference
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Uinput1 = findViewById(R.id.Uinput1p2)
        Uinput2 = findViewById(R.id.Uinput2p2)
        Uinput3 = findViewById(R.id.Uinput3p2)
        Uinput4 = findViewById(R.id.Uinput4p2)

        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("User")


        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Handle data changes
                for (userSnapshot in dataSnapshot.children) {
                    val user = userSnapshot.getValue(UserData::class.java)
                    // Do something with the user data
                    /*Uinput1.setText(user?.name)
                    Uinput2.setText(user?.address)
                    Uinput3.setText(user?.email)
                    Uinput4.setText(user?.phone)*/
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle cancelled event
                Toast.makeText(this@MainActivity2, "Failed to read value.", Toast.LENGTH_SHORT).show()
            }
        })

        // add click listeners to the edit buttons
        findViewById<Button>(R.id.Bt2p2).setOnClickListener {
            val newName = Uinput1.text.toString()
            myRef.child(userId).child("name").setValue(newName)
        }

        findViewById<Button>(R.id.Bt3p2).setOnClickListener {
            val newAddress = Uinput2.text.toString()
            myRef.child(userId).child("address").setValue(newAddress)
        }

        findViewById<Button>(R.id.Bt4p2).setOnClickListener {
            val newEmail = Uinput3.text.toString()
            myRef.child(userId).child("email").setValue(newEmail)
        }

        findViewById<Button>(R.id.New1).setOnClickListener {
            val newPhone = Uinput4.text.toString()
            myRef.child(userId).child("phone").setValue(newPhone)
        }

        // add click listener to the delete button
        findViewById<Button>(R.id.Bt5p2).setOnClickListener {
            myRef.child(userId).removeValue()
            // TODO: navigate to previous activity or show a confirmation message
        }

        // add click listener to the navigate button
        findViewById<Button>(R.id.Bt6p2).setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}






