package com.example.vetdictionary

import java.io.Serializable

data class Vocabulary(var vocab_id:Int, var vocab_english:String, var vocab_pronoun:String, var vocab_turkish:String) : Serializable {
}