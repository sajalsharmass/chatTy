package com.example.chatty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var edt_mail : EditText
    private lateinit var edt_password : EditText
    private lateinit var btn_login : Button
    private lateinit var btn_signup : Button
    private lateinit var mAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edt_mail = findViewById(R.id.edt_mail)
        edt_password = findViewById(R.id.edt_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            val email = edt_mail.text.toString()
            val password = edt_password.text.toString()

            login_fun(email,password);
        }
    }

    private fun login_fun(email: String,password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@login, MainPage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@login, "User does no exist", Toast.LENGTH_SHORT).show()
                }
            }
    }
}