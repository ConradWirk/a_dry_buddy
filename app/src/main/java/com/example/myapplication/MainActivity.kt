package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roslina1 = findViewById<Button>(R.id.Roslina1)
        roslina1.setOnClickListener(View.OnClickListener {  val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)})
        val roslina2 = findViewById<Button>(R.id.Roslina2)
        roslina2.setOnClickListener(View.OnClickListener {  val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)})
        val roslina3 = findViewById<Button>(R.id.Roslina3)
        roslina3.setOnClickListener(View.OnClickListener {  val intent = Intent(this, Roslina1::class.java)
            startActivity(intent)})
    }
}