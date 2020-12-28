package com.example.vetdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_vocabulary.*
import kotlinx.android.synthetic.main.alertdialog_view.*

class VocabularyActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var listOfVocabulary:ArrayList<Vocabulary>
    private lateinit var adapter: VocabularyAdabter
    private lateinit var refVocabulary: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        //recycle view düzenlemesi
        rvVocabulary.setHasFixedSize(true)
        rvVocabulary.layoutManager = LinearLayoutManager(this@VocabularyActivity)

        //firebase bağlandı
        val db = FirebaseDatabase.getInstance()
        refVocabulary = db.getReference("vocabulary")

        listOfVocabulary = ArrayList()
        //adapter bağlantısı
        adapter = VocabularyAdabter(this@VocabularyActivity,listOfVocabulary,refVocabulary)
        rvVocabulary.adapter = adapter

        showAllOfVocabulary()

        //toolbar bilgisi
        toolbarVocabulary.title = "Vocabulary List"

        setSupportActionBar(toolbarVocabulary)

        
    }

    //search tool
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu tanıtıldı
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        return true
    }

    //item seçme
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.actionSearch -> {
                val searchView = item?.actionView as SearchView
                searchView.setOnQueryTextListener(this)
                return true
            }

            R.id.actionAdd -> {
                showAlertDialog()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
    //tüm verileri çağırma
    fun showAllOfVocabulary(){
        refVocabulary.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                listOfVocabulary.clear()

                for (c in p0.children){
                    val nodes = c.getValue(Vocabulary::class.java)

                    if (nodes != null){
                        nodes.vocab_id = c.key
                        listOfVocabulary.add(nodes)
                    }
                }
                adapter.notifyDataSetChanged()
                //listenin boyutu
                toolbarVocabulary.subtitle = "You have ${listOfVocabulary.size} words"
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    //Search verileri çağırma
    fun makeSearch(searchedWord:String){
        refVocabulary.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //verilerin üst üste binemesi için liste temizlendi
                listOfVocabulary.clear()

                for (c in p0.children){
                    val nodes = c.getValue(Vocabulary::class.java)

                    if (nodes != null){
                        if (nodes.vocab_english!!.contains(searchedWord))
                        {
                            nodes.vocab_id = c.key
                            listOfVocabulary.add(nodes)
                        }
                    }
                }
                //node nesnesi değişiklerde adptera aktarılacak
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    // alertdialog bağlantısı
    fun showAlertDialog(){
        val aView = LayoutInflater.from(this@VocabularyActivity).inflate(R.layout.alertdialog_view,null)
        val editTextEnglish = aView.findViewById(R.id.editTextEnglish) as EditText
        val editTextPronounce = aView.findViewById(R.id.editTextPronounce) as EditText
        val editTextTurkish = aView.findViewById(R.id.editTextTurkish) as EditText

        val alertName =AlertDialog.Builder(this@VocabularyActivity)

        alertName.setTitle("Add a new Word")
        alertName.setView(aView)
        alertName.setPositiveButton("Add"){ dialogInterface, i ->
            val word_english = editTextEnglish.text.toString().trim()
            val word_pron = editTextPronounce.text.toString().trim()
            val word_turkish = editTextTurkish.text.toString().trim()

            // Fire base veri ekleme
            if (word_english.isNotEmpty() && word_pron.isNotEmpty() && word_turkish.isNotEmpty()){
                val word = Vocabulary("",word_english,word_pron,word_turkish)
                refVocabulary.push().setValue(word)
                Toast.makeText(applicationContext,"Informations Saved! => $word_english-$word_pron-$word_turkish",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@VocabularyActivity,"Please, fill all of the blanks",Toast.LENGTH_SHORT).show()
            }


        }
        alertName.setNegativeButton("Cancel"){ dialogInterface, i ->

        }

        alertName.create().show()
    }
    //searc buton gönderilen arama
    override fun onQueryTextSubmit(query: String): Boolean {
        makeSearch(query)
        return true
    }
    // search button yazdıkça arama
    override fun onQueryTextChange(newText: String): Boolean {
        makeSearch(newText)
        return true
    }
}