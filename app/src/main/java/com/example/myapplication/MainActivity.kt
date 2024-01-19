package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        val IP = "https://raw.githubusercontent.com/ConradWirk/a_dry_buddy/master"

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
        val wil1 = findViewById<ProgressBar>(R.id.progressBar2)
        wil1.setOnClickListener{

            fetchUrlProgressBar("$IP/wil1", R.id.progressBar2)
        }
        val wil2 = findViewById<ProgressBar>(R.id.progressBar4)
        wil2.setOnClickListener{

            fetchUrlProgressBar("$IP/wil12", R.id.progressBar4)
        }
        val wil3 = findViewById<ProgressBar>(R.id.progressBar5)
        wil3.setOnClickListener{

            fetchUrlProgressBar("$IP/wil3", R.id.progressBar5)
        }
        val swiatlo = findViewById<ProgressBar>(R.id.progressBar7)
        swiatlo.setOnClickListener{

            fetchUrlProgressBar("$IP/swiatlo", R.id.progressBar7)
        }
        val temperatura = findViewById<TextView>(R.id.temperatura)
        temperatura.setOnClickListener{

            fetchUrlTextView("$IP/temperatura", R.id.temperatura)
        }
    }

    private fun fetchUrlTextView(urlString: String, type: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Replace "https://example.com" with the actual URL you want to fetch
                //val urlString = "https://raw.githubusercontent.com/ConradWirk/a_dry_buddy/master/.gitignore"
                val content = withContext(Dispatchers.IO) {
                    // Perform network operation in IO dispatcher
                    fetchDataFromUrl(urlString)
                }
                // Now 'content' variable holds the string content from the URL
                // You can use the 'content' variable as needed, such as updating UI
                println("Content from $urlString:\n$content")
                var x = findViewById<TextView>(type)
                x.text = content
            } catch (e: Exception) {
                e.printStackTrace() // Handle exceptions, e.g., network error
            }
        }
    }

    private fun fetchUrlProgressBar(urlString: String, type: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Replace "https://example.com" with the actual URL you want to fetch
                //val urlString = "https://raw.githubusercontent.com/ConradWirk/a_dry_buddy/master/.gitignore"
                val content = withContext(Dispatchers.IO) {
                    // Perform network operation in IO dispatcher
                    fetchDataFromUrl(urlString)
                }
                // Now 'content' variable holds the string content from the URL
                // You can use the 'content' variable as needed, such as updating UI
                println("Content from $urlString:\n$content")
                var x = findViewById<ProgressBar>(type)
                x.progress = content.trim().toInt()
            } catch (e: Exception) {
                e.printStackTrace() // Handle exceptions, e.g., network error
            }
        }
    }
    private fun fetchDataFromUrl(urlString: String): String {
        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        return try {
            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val content = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                content.append(line).append("\n")
            }

            content.toString()
        } finally {
            connection.disconnect()
        }
    }
}