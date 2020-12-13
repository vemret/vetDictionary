package com.example.vetdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_vocabulary.*

class VocabularyActivity : AppCompatActivity() {

    private lateinit var listOfVocabulary:ArrayList<Vocabulary>
    private lateinit var adapter: VocabularyAdabter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)



        rvVocabulary.setHasFixedSize(true)
        rvVocabulary.layoutManager = LinearLayoutManager(this)

        listOfVocabulary = ArrayList()

        val v1 = Vocabulary(1,"table","teybıl","masa")
        val v2 = Vocabulary(2,"book","buuk","kitap")
        val v3 = Vocabulary(3,"mause","maus","fare")

        listOfVocabulary.add(v1)
        listOfVocabulary.add(v2)
        listOfVocabulary.add(v3)

        adapter = VocabularyAdabter(this,listOfVocabulary)
        rvVocabulary.adapter = adapter


        toolbarVocabulary.title = "Vocabulary List"
        toolbarVocabulary.subtitle = "You have ${listOfVocabulary.size} words"
        setSupportActionBar(toolbarVocabulary)
    }
}