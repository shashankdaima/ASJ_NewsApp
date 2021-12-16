package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding
import com.example.androidstudyjam1.models.Article
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {
    private lateinit var adapter: NewsRecyclerViewAdapter
    private lateinit var binding: LatestNewsFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)
        setHasOptionsMenu(true)
        adapter = NewsRecyclerViewAdapter{
            makeShortToast(it.url)
        }
        binding.allNewRecyclerView.apply {
            adapter = this@LatestNewsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        val list = listOf(
            Article(
                author = "First Author",
                content = "Some lorem Ipsum for you.",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                publishedAt = "Today",
                title = "First News",
                url = "www.google.com",
                urlToImage = ""
            ),
            Article(
                author = "Second Author",
                content = "Some lorem Ipsum for you.2",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                publishedAt = "Today",
                title = "Second News",
                url = "www.yahoo.com",
                urlToImage = ""
            ),
            Article(
                author = "Third Author",
                content = "Some lorem Ipsum for you.",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                publishedAt = "Today",
                title = "Third News",
                url = "www.reddit.com",
                urlToImage = ""
            ),
        )
        adapter.submitList(list)
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings -> {
                    makeShortToast("Settings is Clicked")
                    true
                }
                R.id.notification -> {
                    makeShortToast("Notification is Clicked")

                    true
                }
                R.id.search -> {
                    makeShortToast("Search is Clicked")

                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_menu, menu)
    }
}