package com.example.vetdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var listOfCategories: ArrayList<Categories>
    private lateinit var adapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar_main.title="Vet Dictionary"
        setSupportActionBar(toolbar_main)

        rvHome.setHasFixedSize(true)
        rvHome.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        listOfCategories = ArrayList()
        val c1 = Categories(1,"Vocabulary List","ic_baseline_library_books_24")
        val c2 = Categories(2,"Add a new Word","ic_baseline_library_add_24")
        val c3 = Categories(3,"Quiz","ic_exercises")
        val c4 = Categories(4,"Important Words","ic_favorite")
        listOfCategories.add(c1)
        listOfCategories.add(c2)
        listOfCategories.add(c3)
        listOfCategories.add(c4)

        adapter = CategoryAdapter(this@MainActivity,listOfCategories)

        rvHome.adapter = adapter
    }

    //geri tuşu basılınca tekrar tekrar acılma sorunu
    override fun onBackPressed() {
        //ana inten oluşturuldu
        val intent = Intent(Intent.ACTION_MAIN)
        //intente home ekranına geri donme özelliği verildi
        intent.addCategory(Intent.CATEGORY_HOME)
        //yeni bir görev başlatıldı
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        //intent çalıştırıldı
        startActivity(intent)
    }

}