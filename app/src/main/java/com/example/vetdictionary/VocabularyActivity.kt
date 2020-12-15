package com.example.vetdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.android.synthetic.main.alertdialog_view.*

class VocabularyActivity : AppCompatActivity() {

    private lateinit var listOfVocabulary:ArrayList<Vocabulary>
    private lateinit var adapter: VocabularyAdabter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)



        rvVocabulary.setHasFixedSize(true)
        rvVocabulary.layoutManager = LinearLayoutManager(this)

        listOfVocabulary = ArrayList()

        val v1 = Vocabulary(1,"table","teybıl","masa",false)
        val v2 = Vocabulary(2,"book","buuk","kitap",true)
        val v3 = Vocabulary(3,"mause","maus","fare",false)

        listOfVocabulary.add(v1)
        listOfVocabulary.add(v2)
        listOfVocabulary.add(v3)

        adapter = VocabularyAdabter(this,listOfVocabulary)
        rvVocabulary.adapter = adapter


        toolbarVocabulary.title = "Vocabulary List"
        toolbarVocabulary.subtitle = "You have ${listOfVocabulary.size} words"
        setSupportActionBar(toolbarVocabulary)
    }

    // alertdialog bağlantısı
    fun showAlertDialog(){
        val aView = LayoutInflater.from(this).inflate(R.layout.alertdialog_view,null)
        val editTextEnglish = aView.findViewById(R.id.editTextEnglish) as EditText
        val editTextPronounce = aView.findViewById(R.id.editTextPronounce) as EditText
        val editTextTurkish = aView.findViewById(R.id.editTextTurkish) as EditText

        val alertName =AlertDialog.Builder(this)

        alertName.setTitle("Edit Word")
        alertName.setView(aView)
        alertName.setPositiveButton("Edit"){ dialogInterface, i ->
            val word_english = editTextEnglish.text.toString().trim()
            val word_pron = editTextPronounce.text.toString().trim()
            val word_turkish = editTextTurkish.text.toString().trim()

            Toast.makeText(applicationContext,"Information Updated!",Toast.LENGTH_SHORT).show()
        }
        alertName.setNegativeButton("Cancel"){ dialogInterface, i ->

        }

        alertName.create().show()
    }
}