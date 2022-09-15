package com.vagrok.vag_collar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.vagrok.vag_collar.R

class MainActivity2 : AppCompatActivity() {
    private lateinit var qrcodebtn : Button
    private lateinit var manuelbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        qrcodebtn = findViewById(R.id.qrcodebtn)
        manuelbtn = findViewById(R.id.manuelbtn)

        qrcodebtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this , CowScannerActivity::class.java)
            startActivity(intent)
        })
        manuelbtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this , NewCowActivity::class.java)
            startActivity(intent)
        })
    }
}