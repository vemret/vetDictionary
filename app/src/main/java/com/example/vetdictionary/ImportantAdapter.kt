package com.example.vetdictionary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class ImportantAdapter(private val mContext: Context
                       , private val listOfImportant: List<Vocabulary>
                        , private val refImportant: DatabaseReference) : RecyclerView.Adapter<ImportantAdapter.CardViewKeeper>(){

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
            holder.imageViewImportant.setImageResource(R.drawable.ic_star_border)

            refImportant.child(important.vocab_id!!).child("vocab_important").setValue(false)
        }
        holder.importantCard.setOnClickListener {
            val ad = AlertDialog.Builder(mContext)

            ad.setMessage(important.vocab_turkish)
            ad.setTitle(important.vocab_english)
            ad.setIcon(R.drawable.ic_alert)

            ad.create().show()
        }
    }
}