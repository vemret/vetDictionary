package com.example.vetdictionary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val mContext:Context, private val listOfCategories : List<Categories>) : RecyclerView.Adapter<CategoryAdapter.CardViewKeeper>() {

    inner class CardViewKeeper(cView:View) : RecyclerView.ViewHolder(cView){
        var home_card : CardView
        var textViewListName:TextView
        var imageViewList:ImageView

        init {
            home_card=cView.findViewById(R.id.home_card)
            textViewListName=cView.findViewById(R.id.textViewListName)
            imageViewList=cView.findViewById(R.id.imageViewList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewKeeper {
        val cView=LayoutInflater.from(mContext).inflate(R.layout.main_card_view,parent,false)
        return CardViewKeeper(cView)
    }

    override fun getItemCount(): Int {
        return listOfCategories.size
    }

    override fun onBindViewHolder(holder: CardViewKeeper, position: Int) {
        val categories = listOfCategories.get(position)
        holder.textViewListName.text=categories.category_name
        //view'e ait icon, dosya ismi ve paket ismi verildi
        holder.imageViewList.setImageResource(mContext.resources.getIdentifier(categories.category_icon,"drawable",mContext.packageName))

        holder.home_card.setOnClickListener{
            if (holder.textViewListName.text=="Vocabulary List") {
                val intent = Intent(mContext, VocabularyActivity::class.java)
                mContext.startActivity(intent)
            }

            if (holder.textViewListName.text == "Add a new Word"){
                val intent = Intent(mContext, AddActivity::class.java)
                mContext.startActivity(intent)
            }
        }
    }
}