package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roslina1 = findViewById<Button>(R.id.Roslina1)
        roslina1.setOnClickListener {
            val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)
        }
        val roslina2 = findViewById<Button>(R.id.Roslina2)
        roslina2.setOnClickListener {
            val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)
        }
        val roslina3 = findViewById<Button>(R.id.Roslina3)
        roslina3.setOnClickListener {
            val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)
        }
        val FloraIncognita = findViewById<Button>(R.id.floraincognita)
        FloraIncognita.setOnClickListener {
            val launchIntent = packageManager.getLaunchIntentForPackage("com.floraincognita.app.floraincognita")
            startActivity(launchIntent)
        }
    }
}