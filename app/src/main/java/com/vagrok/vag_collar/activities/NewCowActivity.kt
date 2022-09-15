package com.vagrok.vag_collar.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vagrok.vag_collar.R
import com.vagrok.vag_collar.databinding.ActivityMainBinding


class NewCowActivity : AppCompatActivity() {

    private  lateinit var btnSave : Button
//this is a test
    private lateinit var dropBox : AutoCompleteTextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cow)

        btnSave = findViewById(R.id.btnSend)
         dropBox = findViewById(R.id.txtReg)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        btnSave.setOnClickListener(View.OnClickListener {
            val intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)
        })
        val feelings = resources.getStringArray(R.array.animal_types_strings)
        val arrayAdapter = ArrayAdapter(this, R.layout.list_item, feelings)

       dropBox.setAdapter(arrayAdapter)

    }
}