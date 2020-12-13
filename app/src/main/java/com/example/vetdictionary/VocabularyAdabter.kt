package com.example.vetdictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class VocabularyAdabter(private val mContext:Context,private val listofVocabulary:List<Vocabulary>) : RecyclerView.Adapter<VocabularyAdabter.CardViewKeeper>() {

    inner class CardViewKeeper(vView: View) : RecyclerView.ViewHolder(vView){
        var vocabCard : CardView
        var tvEnglish: TextView
        var tvPronounce: TextView
        var imageViewPopup: ImageView

        init {
            vocabCard=vView.findViewById(R.id.vocabCard)
            tvEnglish=vView.findViewById(R.id.tvEnglish)
            tvPronounce=vView.findViewById(R.id.tvPronounce)
            imageViewPopup=vView.findViewById(R.id.imageViewPopup)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewKeeper {
        val vView = LayoutInflater.from(mContext).inflate(R.layout.vocabulary_card_view,parent,false)
        return CardViewKeeper(vView)
    }

    override fun getItemCount(): Int {
        return listofVocabulary.size
    }

    override fun onBindViewHolder(holder: CardViewKeeper, position: Int) {
        val vocab = listofVocabulary.get(position)
        holder.tvEnglish.text = vocab.vocab_english
        holder.tvPronounce.text = vocab.vocab_pronoun

        holder.imageViewPopup.setOnClickListener{

        }
    }
}