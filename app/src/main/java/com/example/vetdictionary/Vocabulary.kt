package com.example.vetdictionary

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
@IgnoreExtraProperties
data class Vocabulary(var vocab_id:String?=""
                      , var vocab_english:String?=""
                      , var vocab_pronoun:String?=""
                      , var vocab_turkish:String?=""
                      , var vocab_important:Boolean?=false) : Serializable {
}