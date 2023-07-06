package com.example.slcraft

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity4 : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var resendButton: Button
    private lateinit var confirmButton: Button
    private var verificationId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        editText = findViewById(R.id.editTextText)
        resendButton = findViewById(R.id.Bt1p4)
        confirmButton = findViewById(R.id.button2)

        resendButton.setOnClickListener { resendVerificationCode() }
        confirmButton.setOnClickListener { verifyCode() }
    }

    private fun resendVerificationCode() {
        val phoneNumber = editText.text.toString()

        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    // Verification completed automatically
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // Verification failed
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    // Code successfully sent, handle the verificationId and token if needed
                    this@MainActivity4.verificationId = verificationId
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyCode() {
        val code = editText.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId ?: "", code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Verification successful, navigate to MainActivity5
                    navigateToMainActivity5()
                } else {
                    // Verification failed
                }
            }
    }

    private fun navigateToMainActivity5() {
        val intent = Intent(this, MainActivity5::class.java)
        startActivity(intent)
    }
}














