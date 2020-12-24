package com.example.vetdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        radioButtonA.setOnClickListener {
            startActivity(Intent(this@QuestionActivity,QuizResultActivity::class.java))
            finish()
        }
    }
}