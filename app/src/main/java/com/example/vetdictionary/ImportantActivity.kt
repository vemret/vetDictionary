package com.example.vetdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_important.*

class ImportantActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var listOfVocabulary : ArrayList<Vocabulary>
    private lateinit var adapter: ImportantAdapter
    private lateinit var refImportantVoabulary : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important)


        rvImportant.setHasFixedSize(true)
        rvImportant.layoutManager = LinearLayoutManager(this@ImportantActivity)


        val db = FirebaseDatabase.getInstance()
        refImportantVoabulary = db.getReference("vocabulary")

        listOfVocabulary = ArrayList()

        adapter = ImportantAdapter(this@ImportantActivity,listOfVocabulary,refImportantVoabulary)
        rvImportant.adapter = adapter

        //vocab_ important == true olan verileri göster
        showImportantOfVocabulary(true)


        toolbarImportant.title = "Important words for you"
        setSupportActionBar(toolbarImportant)

    }

    //Search verileri çağırma
    fun makeSearch(searchedWord:String,vocab_important:Boolean){
        val query = refImportantVoabulary.orderByChild("vocab_important").equalTo(vocab_important)
        query.addValueEventListener(object : ValueEventListener {
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

    //Favori verileri çağırma
    fun showImportantOfVocabulary(vocab_important:Boolean){
        // favori veriler query olark tanımlandı
        val query = refImportantVoabulary.orderByChild("vocab_important").equalTo(vocab_important)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //verilerin üst üste binemesi için liste temizlendi
                listOfVocabulary.clear()

                for (c in p0.children){
                    val nodes = c.getValue(Vocabulary::class.java)

                    if (nodes != null){
                        nodes.vocab_id = c.key
                        listOfVocabulary.add(nodes)
                    }
                }
                //node nesnesi değişiklerde adptera aktarılacak
                adapter.notifyDataSetChanged()
                //liste boyutu
                toolbarImportant.subtitle = "You have ${listOfVocabulary.size}  important words"
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menu tanıtıldı
        menuInflater.inflate(R.menu.important_toolbar_menu,menu)

        return true
    }

    //item seçme
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_search_important -> {
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        makeSearch(query,true)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        makeSearch(newText,true)
        return true
    }
}