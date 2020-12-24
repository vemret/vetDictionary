package com.example.vetdictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.PopupMenu
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        var questionAmount:Int = 0

        ivPopupQuantity.setOnClickListener {
            val popupMenu=PopupMenu(this@QuizActivity,ivPopupQuantity)
            popupMenu.menuInflater.inflate(R.menu.quiz_start_popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item->
                when(item.itemId)
                {
                    R.id.action_five -> {
                        questionAmount=5
                        tvQuantity.text = "$questionAmount Question Ready!"
                        true
                    }
                    R.id.action_fifteen -> {
                        questionAmount=15
                        tvQuantity.text = "$questionAmount Question Ready!"
                        true
                    }
                    R.id.action_thirty -> {
                        questionAmount=30
                        tvQuantity.text = "$questionAmount Question Ready!"
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()

        }
        btnQuizStart.setOnClickListener {
            startActivity(Intent(this@QuizActivity,QuestionActivity::class.java))
        }


    }
}