package com.example.vetdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.android.synthetic.main.main_card_view.*

class AddActivity : AppCompatActivity() {
    lateinit var refAdd:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        toolbarAdd.title = "VET Dictionary"
        toolbarAdd.subtitle = "Add Page"
        setSupportActionBar(toolbarAdd)

        //firebase bağlandı
        val db = FirebaseDatabase.getInstance()
        refAdd = db.getReference("vocabulary")


        imageButtonDoneAdd.setOnClickListener {
            val english = editTextEnglishAdd.text.toString().trim()
            val pronounce = editTextPronounAdd.text.toString().trim()
            val turkish = editTextTurkishAdd.text.toString().trim()

            // Fire base veri ekleme
            if (english.isNotEmpty() && pronounce.isNotEmpty() && turkish.isNotEmpty()){
                val word = Vocabulary("",english,pronounce,turkish)
                refAdd.push().setValue(word)
                Toast.makeText(applicationContext,"Informations Saved! => $english-$pronounce-$turkish",Toast.LENGTH_LONG).show()

                editTextEnglishAdd.text.clear()
                editTextPronounAdd.text.clear()
                editTextTurkishAdd.text.clear()
            }else{
                Toast.makeText(this@AddActivity,"Please, fill all of the blanks",Toast.LENGTH_SHORT).show()
            }
        }
        imageButtonCancelAdd.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}