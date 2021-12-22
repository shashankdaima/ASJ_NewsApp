package com.example.androidstudyjam1.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.androidstudyjam1.models.Article

object NewsDiffUtilComparator : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}