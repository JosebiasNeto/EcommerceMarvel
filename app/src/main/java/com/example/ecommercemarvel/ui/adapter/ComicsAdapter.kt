package com.example.ecommercemarvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercemarvel.R
import com.example.ecommercemarvel.data.model.Comic
import com.squareup.picasso.Picasso

class ComicsAdapter(private val comics: ArrayList<Comic>) :
    RecyclerView.Adapter<ComicsAdapter.ComicsHolder>() {

    class ComicsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: Comic) {
            itemView.apply {
                Picasso.get().load(comic.imageResponse.image)
                    .into(itemView.findViewById<ImageView>(R.id.iv_comic))
                findViewById<TextView>(R.id.tv_title_comic).text = comic.title
                findViewById<TextView>(R.id.tv_price_comic).text =
                    comic.priceResponse.price.toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder =
        ComicsHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_item, parent, false))

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) =
        holder.bind(comics[position])

    override fun getItemCount(): Int = comics.size
}