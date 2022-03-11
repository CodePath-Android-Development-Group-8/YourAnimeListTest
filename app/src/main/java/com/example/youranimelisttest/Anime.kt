package com.example.youranimelisttest

import org.json.JSONArray

data class Anime(
    val title: String
    ) {

    // val animeImageUrl = "https://something// main_picture
    companion object {
        fun fromJsonArray(animeJsonArray: JSONArray): MutableList<Anime> {
            val animes = mutableListOf<Anime>()
            for (i in 0 until animeJsonArray.length()) {
                val animeNode = animeJsonArray.getJSONObject(i).getJSONObject("node")
                animes.add(
                    Anime(
                        animeNode.getString("title"))
                    )
                }
            return animes
        }
    }
}