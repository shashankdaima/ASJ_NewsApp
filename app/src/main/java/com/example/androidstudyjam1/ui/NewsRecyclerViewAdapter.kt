package com.example.androidstudyjam1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyjam1.databinding.NewsRecyclerviewElementBinding
import com.example.androidstudyjam1.models.Article

class NewsRecyclerViewAdapter(private val onClick: (Article) -> Unit) :
    ListAdapter<Article, NewsRecyclerViewAdapter.ViewHolder>(NewsDiffUtilComparator) {
    inner class ViewHolder(val binding: NewsRecyclerviewElementBinding) :
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

    companion object {
        private object NewsDiffUtilComparator : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }
}