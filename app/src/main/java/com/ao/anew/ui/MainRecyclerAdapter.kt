package com.ao.anew.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ao.anew.R
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ao.anew.model.Article
import com.ao.anew.util.DateTimeUtils
import java.text.DateFormat
import javax.inject.Inject

class MainRecyclerAdapter
    @Inject constructor(private val requestManager: RequestManager):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.publishedAt == newItem.publishedAt
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)
    private var interaction: Interaction? = null

    fun setInteraction(interaction: Interaction){
        this.interaction = interaction
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_news_light,
                parent,
                false
            ),
            interaction,
            requestManager
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Article>) {
        differ.submitList(list)
    }

    class NewsViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.title)
        val subtitle : TextView = itemView.findViewById(R.id.subtitle)

        fun bind(item: Article) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

             title.text = item.title
             subtitle.text = item.author
           /* itemView.date.text = item.publishedAt
            itemView.date.text = DateFormat.getDateInstance(DateFormat.FULL).format(DateTimeUtils.getFormattedDate(item.publishedAt))

            requestManager
                .load(item.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(itemView.image)*/


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Article)
    }
}


