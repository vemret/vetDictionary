package com.example.vetdictionary

import java.io.Serializable

data class Categories(var category_id: Int, var category_name: String, var category_icon: String) : Serializable {
}