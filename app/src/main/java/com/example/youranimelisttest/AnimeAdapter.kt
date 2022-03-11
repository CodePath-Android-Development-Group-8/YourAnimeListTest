package com.example.youranimelisttest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AnimeAdapter(private val context: Context, private val animes: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = animes[position]
        holder.bind(anime)

    }

    override fun getItemCount() = animes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bind(anime: Anime) {
            tvTitle.text = anime.title
        }
    }


}
