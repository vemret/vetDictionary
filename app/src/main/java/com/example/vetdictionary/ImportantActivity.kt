package com.example.vetdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_important.*

class ImportantActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var listOfVocabulary : ArrayList<Vocabulary>
    private lateinit var adapter: ImportantAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important)


        rvImportant.setHasFixedSize(true)
        rvImportant.layoutManager = LinearLayoutManager(this@ImportantActivity)

        listOfVocabulary = ArrayList()
        var listOfImportant = ArrayList<Vocabulary>()

        val v1 = Vocabulary(1,"table","teybıl","masa",false)
        val v2 = Vocabulary(2,"book","buuk","kitap",true)
        val v3 = Vocabulary(3,"mause","maus","fare",true)

        listOfVocabulary.add(v1)
        listOfVocabulary.add(v2)
        listOfVocabulary.add(v3)

        for (i in listOfVocabulary){
            if (i.vocab_important) {
                listOfImportant.add(i)
            }
        }


        adapter = ImportantAdapter(this@ImportantActivity,listOfImportant)
        rvImportant.adapter = adapter

        toolbarImportant.title = "Important words for you"
        toolbarImportant.subtitle = "You have ${listOfImportant.size} words"
        setSupportActionBar(toolbarImportant)

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
                val searchView = item?.actionView as SearchView
                searchView.setOnQueryTextListener(this)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("gonderilen arama",query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("arama yaptıkça",newText!!)
        return true
    }
}