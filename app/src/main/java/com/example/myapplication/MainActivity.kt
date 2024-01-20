package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
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

    //private lateinit var userInputEditText: EditText
    private var daneIP: String = "raw.githubusercontent.com/ConradWirk/a_dry_buddy/master"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        //var ip = "https://raw.githubusercontent.com/ConradWirk/a_dry_buddy/master"
        var ip = "https://$daneIP"

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ustawienia = findViewById<Button>(R.id.settings)
        ustawienia.setOnClickListener{
            showPopup()
        }
        val xddd = findViewById<Button>(R.id.button4)
        xddd.setOnClickListener{
            showPopup()
        }

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
            ip = "https://$daneIP"
            fetchUrlProgressBar("$ip/wil1", R.id.progressBar2)
            println(ip)
        }
        val wil2 = findViewById<ProgressBar>(R.id.progressBar4)
        wil2.setOnClickListener{
            ip = "https://$daneIP"
            fetchUrlProgressBar("$ip/wil2", R.id.progressBar4)
        }
        val wil3 = findViewById<ProgressBar>(R.id.progressBar5)
        wil3.setOnClickListener{
            ip = "https://$daneIP"
            fetchUrlProgressBar("$ip/wil3", R.id.progressBar5)
        }
        val swiatlo = findViewById<ProgressBar>(R.id.progressBar7)
        swiatlo.setOnClickListener{
            ip = "https://$daneIP"
            fetchUrlProgressBar("$ip/swiatlo", R.id.progressBar7)
        }
        val temperatura = findViewById<TextView>(R.id.temperatura)
        temperatura.setOnClickListener{
            ip = "https://$daneIP"
            fetchUrlTextView("$ip/temperatura", R.id.temperatura)
        }


    }

    private fun fetchUrlTextView(urlString: String, type: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val content = withContext(Dispatchers.IO) {
                    // Perform network operation in IO dispatcher
                    fetchDataFromUrl(urlString)
                }
                // Now 'content' variable holds the string content from the URL
                // You can use the 'content' variable as needed, such as updating UI
                println("Content from $urlString:\n$content")
                val TextViewID = findViewById<TextView>(type)
                TextViewID.text = content
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
                val viewID = findViewById<ProgressBar>(type)
                viewID.progress = content.trim().toFloat().toInt()
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

    private fun showPopup() {
        val inflater = LayoutInflater.from(this)
        val popupView = inflater.inflate(R.layout.dialog_input, null)

        val editTextPopupInput: EditText = popupView.findViewById(R.id.editTextDialogInput)
        val buttonPopupSave: Button = popupView.findViewById(R.id.save)

        val dialog = AlertDialog.Builder(this)
            .setView(popupView)
            .setTitle("Wprowadź IP urządzenia")
            .setCancelable(true)
            .create()

        buttonPopupSave.setOnClickListener {
            val inputData = editTextPopupInput.text.toString()
            daneIP = inputData
            dialog.dismiss()
            println(daneIP)
        }

        dialog.show()
    }
}