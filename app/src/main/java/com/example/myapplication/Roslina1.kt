package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Roslina1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roslina_opis)
        val powrot = findViewById<View>(R.id.Powrot) as Button
        powrot.setOnClickListener { finish() }
    }
}