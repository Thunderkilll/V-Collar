package com.vagrok.vag_collar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.vagrok.vag_collar.R

class HomeActivity : AppCompatActivity() {
    private lateinit var todoB : Button
    private lateinit var editProfileB : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        todoB = findViewById(R.id.todoB)
        editProfileB = findViewById(R.id.editProfileB)

        todoB.setOnClickListener(View.OnClickListener {
            //direct to pic how to add new animal activity
            val intent = Intent(this , MainActivity2::class.java)
            startActivity(intent)
        })
        editProfileB.setOnClickListener(View.OnClickListener {
            //direct to pic how to add new animal activity
            val intent = Intent(this , NewCowActivity::class.java)
            startActivity(intent)
        })
    }
}