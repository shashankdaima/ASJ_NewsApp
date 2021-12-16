package com.example.androidstudyjam1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyjam1.databinding.NewsRecyclerviewElementBinding
import com.example.androidstudyjam1.models.Article

class NewsRecyclerViewAdapter :
    ListAdapter<Article, NewsRecyclerViewAdapter.ViewHolder>(NewsDiffUtilComparator) {
    inner class ViewHolder(binding: NewsRecyclerviewElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {

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