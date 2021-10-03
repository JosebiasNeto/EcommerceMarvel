package com.example.ecommercemarvel.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommercemarvel.R
import com.example.ecommercemarvel.domain.model.Comic
import com.example.ecommercemarvel.domain.repository.Converters
import com.example.ecommercemarvel.domain.usecase.Rules
import com.example.ecommercemarvel.domain.usecase.Rules.getCheckoutPrice
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class ComicsAdapter(private val comics: ArrayList<Comic>) :
    RecyclerView.Adapter<ComicsAdapter.ComicsHolder>(), Filterable {

    var comicsFilter: ArrayList<Comic> = ArrayList()
    init {
        comicsFilter = comics
    }

    class ComicsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comic: Comic) {
            itemView.apply {
                Picasso.get().load((comic.thumbnail?.path + "." + comic.thumbnail?.extension))
                    .into(itemView.findViewById<ImageView>(R.id.iv_comic))
                findViewById<TextView>(R.id.tv_title_comic).text = comic.title
                findViewById<TextView>(R.id.tv_price_comic).text =
                    Rules.getCheckoutPriceUI(comic.prices[0].price.toFloat())
                findViewById<ImageView>(R.id.star).isVisible = comic.rare == true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsHolder =
        ComicsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comic_item, parent, false)
        )

    override fun onBindViewHolder(holder: ComicsHolder, position: Int) =
        holder.bind(comicsFilter[position])

    override fun getItemCount(): Int = comicsFilter.size

    fun addComics(comics: List<Comic>) {
        this.comics.apply {
            clear()
            addAll(comics)
        }
    }

    fun getComic(position: Int) = comicsFilter[position]

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint?.toString()!!.lowercase() ?: ""
                if(charSearch.isEmpty()){
                    comicsFilter = comics
                } else {
                    val resultList: MutableList<Comic> = ArrayList()
                    comics.filter {
                        it.title!!.lowercase().contains(constraint)
                    }.forEach {
                        resultList.add(it)
                    }
                    comicsFilter = resultList as ArrayList<Comic>
                }
                return FilterResults().apply { values = comicsFilter }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                comicsFilter = if(results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Comic>
                    notifyDataSetChanged()
            }

        }
    }


}