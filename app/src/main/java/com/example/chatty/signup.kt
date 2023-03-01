package com.example.chatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {

    private lateinit var edt_mail : EditText
    private lateinit var edt_password : EditText
    private lateinit var edt_name : EditText
    private lateinit var btn_signup : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edt_mail = findViewById(R.id.edt_mail)
        edt_password = findViewById(R.id.edt_password)
        edt_name = findViewById(R.id.edt_name)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener {
            val email = edt_mail.text.toString()
            val password = edt_password.text.toString()

            signup_fun(email,password)
        }
    }

    private fun signup_fun(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                   val intent = Intent(this@signup, MainPage::class.java)
                    startActivity(intent)
                } else {

                    Toast.makeText(this@signup, "Signup failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}