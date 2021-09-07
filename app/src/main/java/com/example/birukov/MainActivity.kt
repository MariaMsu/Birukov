package com.example.birukov

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView


enum class Taste {
    DELICIOUS, DISGUSTING, HUNGRY
}

class Birukov(private val nFaces: Int, private val nSounds: Int) {
    var faceState: Int = (nFaces - 1) / 2
        private set(value) {
            field = value
        }
    var soundState = 0
        private set(value) {
            field = value
        }
    var textAnswer = ""
        private set(value) {
            field = value
        }

    private fun checkTaste(food: String): Taste {
        return when (food.lowercase()) {
            "" -> Taste.HUNGRY
            "pizza", "pasta" -> Taste.DELICIOUS
            else -> Taste.DISGUSTING
        }
    }

    fun updateEatenFood(food: String) {
        val taste = checkTaste(food)

        textAnswer = when (taste) {
            Taste.HUNGRY -> "Golodno :,("
            Taste.DELICIOUS -> "Mmmm vkusno :)"
            Taste.DISGUSTING -> "Nu takoe :("
        }

        faceState = when (taste) {
            Taste.HUNGRY, Taste.DISGUSTING  -> maxOf(0, faceState - 1)
            Taste.DELICIOUS -> minOf(nFaces - 1, faceState + 1)
        }

        soundState = when (taste) {
            Taste.HUNGRY, Taste.DISGUSTING  -> maxOf(0, soundState - 1)
            Taste.DELICIOUS -> minOf(nSounds - 1, soundState + 1)
        }

        // todo remove this string
        println("    textAnswer: $textAnswer, faceState: $faceState")
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var Andrew: Birukov
    private val faceImgs = arrayOf(
        R.drawable.bir_face_0,
        R.drawable.bir_face_1,
        R.drawable.bir_face_2,
        R.drawable.bir_face_3,
    )

    private val sounds = arrayOf(
        R.raw.huita,
        R.raw.est,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Andrew = Birukov(nFaces = faceImgs.size, nSounds = sounds.size)
    }

    private fun updateTextAnswer() {
        findViewById<TextView>(R.id.textView).apply {
            text = Andrew.textAnswer
        }
    }

    private fun updatePicture() {
        val editImg = findViewById<ImageView>(R.id.imageViewFace)
        editImg.setImageResource(faceImgs[Andrew.faceState])
    }

    private fun playSound(){
        val mMediaPlayer = MediaPlayer.create(this, sounds[Andrew.soundState])
        mMediaPlayer!!.isLooping = false
        mMediaPlayer.start()
    }

    fun sendFood(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextFood)
        Andrew.updateEatenFood(editText.text.toString())

        updateTextAnswer()
        updatePicture()
        playSound()
    }
}