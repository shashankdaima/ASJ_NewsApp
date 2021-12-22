package com.example.androidstudyjam1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyjam1.databinding.NewsRecyclerviewElementBinding
import com.example.androidstudyjam1.models.Article

class NewsRecyclerViewPagingAdapter(val onClick: (Article) -> Unit) :
    PagingDataAdapter<Article, NewsRecyclerViewPagingAdapter.ViewHolder>(NewsDiffUtilComparator) {
    inner class ViewHolder(private val binding: NewsRecyclerviewElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.time.text = article.publishedAt
            binding.title.text = article.title
            binding.description.text = article.description
            binding.root.setOnClickListener {
                onClick(article)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsRecyclerViewPagingAdapter.ViewHolder {
        val binding =
            NewsRecyclerviewElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }


}