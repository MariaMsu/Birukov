package com.example.birukov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
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
    var faceState = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Andrew = Birukov("")
    }

    private fun updateTextAnswer(){
        val editText = findViewById<EditText>(R.id.editTextTextFood)
        Andrew.food = editText.text.toString()

        val textView = findViewById<TextView>(R.id.textView).apply {
            text = Andrew.eatNewFood()
        }
    }

    private fun updatePicture(){
        faceState += 1  // todo change logic
        val editImg = findViewById<ImageView>(R.id.imageViewFace)

        if (faceState % 2 == 0){
            editImg.setImageResource(R.drawable.bir_test_0)
        } else {
            editImg.setImageResource(R.drawable.bir_test_1)
        }
    }

    fun sendFood(view: View) {
        updateTextAnswer()
        updatePicture()
    }
}