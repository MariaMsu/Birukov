package com.example.birukov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class Birukov(food: String) {
    var food = food
    fun eatNewFood(): String{
        return when(food) {
            "" -> "Golodno :,("
            "Pizza", "Pasta" -> "Mmmm vkusno :)"
            else -> "Nu takoe :("
        }
    }
}

class MainActivity : AppCompatActivity() {
    lateinit var Andrew : Birukov

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Andrew = Birukov("")
    }

    fun sendFood(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextFood)
        Andrew.food = editText.text.toString()

        val textView = findViewById<TextView>(R.id.textView).apply {
            text = Andrew.eatNewFood()
        }
    }
}