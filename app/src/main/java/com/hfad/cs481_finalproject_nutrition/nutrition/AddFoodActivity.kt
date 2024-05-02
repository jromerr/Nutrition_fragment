package com.hfad.cs481_finalproject_nutrition.nutrition

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hfad.cs481_finalproject_nutrition.R

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_food)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<Button>(R.id.bBack)
        val buttonAddBreakfast = findViewById<Button>(R.id.bAddBreakfast)
        val buttonAddLunch = findViewById<Button>(R.id.bAddLunch)
        val buttonAddDinner = findViewById<Button>(R.id.bAddDinner)
        val buttonAddSnack = findViewById<Button>(R.id.bAddSnack)
        backButton.setOnClickListener{
            finish()
        }
        buttonAddBreakfast.setOnClickListener{
            val intent = Intent(this, AddBreakfastActivity::class.java)
            startActivity(intent)
        }
    }
}