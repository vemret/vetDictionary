package com.example.vetdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.android.synthetic.main.main_card_view.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        toolbarAdd.title = "VET Dictionary"
        toolbarAdd.subtitle = "Add Page"
        setSupportActionBar(toolbarAdd)


        imageButtonDoneAdd.setOnClickListener {
            val english = editTextEnglishAdd.text.toString().trim()
            val pronounce = editTextPronounAdd.text.toString().trim()
            val turkish = editTextTurkishAdd.text.toString().trim()

            if (TextUtils.isEmpty(english) || TextUtils.isEmpty(pronounce) || TextUtils.isEmpty(turkish)){
                Snackbar.make(toolbarAdd,"Please fill in the blank fields",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this,"Informations Saved! => $english-$pronounce-$turkish", Toast.LENGTH_SHORT).show()
        }
        imageButtonCancelAdd.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}