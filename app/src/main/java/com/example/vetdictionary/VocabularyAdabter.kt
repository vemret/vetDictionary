package com.example.vetdictionary

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

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

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: CardViewKeeper, position: Int) {
        val vocab = listofVocabulary.get(position)
        holder.tvEnglish.text = vocab.vocab_english
        holder.tvPronounce.text = vocab.vocab_pronoun

        if (vocab.vocab_important){
            holder.vocabCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.vocabCard.setCardBackgroundColor(Color.parseColor("#E8E8E8"))
        }


        holder.imageViewPopup.setOnClickListener{
            //menu tasarımı bağlandı
            val popupMenu = PopupMenu(mContext,holder.imageViewPopup)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
            popupMenu.show()
            popupMenu.setForceShowIcon(true);


            //menu işlemleri
            popupMenu.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemId){
                    R.id.actionDelete -> {
                        Snackbar.make(holder.imageViewPopup,"Do you want to delete ${vocab.vocab_english} ?",Snackbar.LENGTH_LONG)
                            .setAction("YES"){

                            }.show()
                        true
                    }
                    R.id.actionEdit -> {
                        showAlertDialog(vocab)
                        true
                    }
                    R.id.actionImportant -> {
                        vocab.vocab_important = !vocab.vocab_important
                        if(vocab.vocab_important){
                            holder.vocabCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                            menuItem.setIcon(R.drawable.ic_favorite)
                        }else{
                            holder.vocabCard.setCardBackgroundColor(Color.parseColor("#E8E8E8"))
                            menuItem.setIcon(R.drawable.ic_exercises)
                        }
                        true
                    }

                    else -> false
                }
            }

        }
    }

    // alertdialog bağlantısı
    fun showAlertDialog(word:Vocabulary){
        val aView = LayoutInflater.from(mContext).inflate(R.layout.alertdialog_view,null)
        val editTextEnglish = aView.findViewById(R.id.editTextEnglish) as EditText
        val editTextPronoun = aView.findViewById(R.id.editTextPronounce) as EditText
        val editTextTurkish = aView.findViewById(R.id.editTextTurkish) as EditText

        //editText uzerinde mevcut bilgiler gösterildi
        editTextEnglish.setText(word.vocab_english)
        editTextPronoun.setText(word.vocab_pronoun)
        editTextTurkish.setText(word.vocab_turkish)

        val alertName = AlertDialog.Builder(mContext)

        alertName.setTitle("Edit Word")
        alertName.setView(aView)
        alertName.setPositiveButton("Edit"){ dialogInterface, i ->
            val word_english = editTextEnglish.text.toString().trim()
            val word_pronoun = editTextPronoun.text.toString().trim()
            val word_turkish = editTextEnglish.text.toString().trim()

            Toast.makeText(mContext,"Information Updated! => $word_english-$word_pronoun-$word_turkish", Toast.LENGTH_SHORT).show()
        }
        alertName.setNegativeButton("Cancel"){ dialogInterface, i ->

        }

        alertName.create().show()
    }
}