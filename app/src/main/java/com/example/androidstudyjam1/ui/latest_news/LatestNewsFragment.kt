package com.example.androidstudyjam1.ui.latest_news

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding
import com.example.androidstudyjam1.ui.ActivityViewModel
import com.example.androidstudyjam1.ui.MainActivity
import com.example.androidstudyjam1.ui.adapters.NewsLoadStateAdapter
import com.example.androidstudyjam1.ui.adapters.NewsRecyclerViewPagingAdapter
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast
import kotlinx.coroutines.flow.collectLatest

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {
    private lateinit var adapter: NewsRecyclerViewPagingAdapter
    private lateinit var binding: LatestNewsFragmentBinding
    private lateinit var searchView: SearchView

    private val viewModel by viewModels<LatestNewsViewModel>()
    private val activityViewModel by activityViewModels<ActivityViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.subtitle = null
        adapter = NewsRecyclerViewPagingAdapter {
            findNavController().navigate(
                LatestNewsFragmentDirections.actionLatestNewsFragmentToWebviewFragment(
                    it
                )
            )
        }
        binding.allNewRecyclerView.apply {
            adapter = this@LatestNewsFragment.adapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { this@LatestNewsFragment.adapter.retry() },
                footer = NewsLoadStateAdapter { this@LatestNewsFragment.adapter.retry() },
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.changeQuery(null)
            Handler().postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 300)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.newsArticles.collectLatest {
                adapter.submitData(it)
            }

        }

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

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView

//        val pendingQuery = viewModel.query.value
//        if (pendingQuery != null && pendingQuery.isNotEmpty()) {
//            searchItem.expandActionView()
//            searchView.setQuery(pendingQuery, false)
//        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.changeQuery(query)
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


    }
}