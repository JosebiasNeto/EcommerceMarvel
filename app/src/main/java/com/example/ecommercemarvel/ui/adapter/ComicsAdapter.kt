package com.example.ecommercemarvel.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercemarvel.R
import com.example.ecommercemarvel.data.model.Comic
import com.squareup.picasso.Picasso

class ComicsAdapter(private val comics: ArrayList<Comic>) :
    RecyclerView.Adapter<ComicsAdapter.ComicsHolder>() {

    class ComicsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: Comic) {
            itemView.apply {
                Picasso.get().load((comic.imageResponse?.path + "." + comic.imageResponse?.extension))
                    .into(itemView.findViewById<ImageView>(R.id.iv_comic))
                findViewById<TextView>(R.id.tv_title_comic).text = comic.title
                findViewById<TextView>(R.id.tv_price_comic).text =
                    getCheckoutPrice(comic.priceResponse[0].price)
                findViewById<ImageView>(R.id.star).isVisible = comic.rare == true
            }
        }
        private fun getCheckoutPrice(price: Float?): String{
            return String.format("%.2f", price)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder =
        ComicsHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_item, parent, false))

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) =
        holder.bind(comics[position])

    override fun getItemCount(): Int = comics.size

    fun addComics(comics: List<Comic>){
        this.comics.apply {
            clear()
            addAll(comics)
        }
    }
    fun getComic(position: Int) = comics[position]


}