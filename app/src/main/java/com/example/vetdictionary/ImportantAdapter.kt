package com.example.vetdictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ImportantAdapter(private val mContext: Context, private val listOfImportant: List<Vocabulary>) : RecyclerView.Adapter<ImportantAdapter.CardViewKeeper>(){

    inner class CardViewKeeper(cView: View) : RecyclerView.ViewHolder(cView){
        var importantCard : CardView
        var tvEnglishImportant : TextView
        var tvPronounceImportant : TextView
        var imageViewImportant : ImageView

        init {
            importantCard=cView.findViewById(R.id.importantCard)
            tvEnglishImportant=cView.findViewById(R.id.tvEnglishImportant)
            tvPronounceImportant = cView.findViewById(R.id.tvPronounceImportant)
            imageViewImportant = cView.findViewById(R.id.imageViewImportant)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewKeeper {
        val cView = LayoutInflater.from(mContext).inflate(R.layout.important_card_view,parent,false)
        return CardViewKeeper(cView)
    }

    override fun getItemCount(): Int {
        return listOfImportant.size
    }

    override fun onBindViewHolder(holder: CardViewKeeper, position: Int) {
        val important = listOfImportant.get(position)

        holder.tvEnglishImportant.text = important.vocab_english
        holder.tvPronounceImportant.text = important.vocab_pronoun
        holder.imageViewImportant.setOnClickListener {

        }
        holder.importantCard.setOnClickListener {

        }
    }
}