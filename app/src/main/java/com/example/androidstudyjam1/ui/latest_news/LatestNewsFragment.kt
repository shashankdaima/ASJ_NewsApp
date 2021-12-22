package com.example.androidstudyjam1.ui.latest_news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
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
import com.example.androidstudyjam1.ui.adapters.NewsRecyclerViewPagingAdapter
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeLongToast
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast
import kotlinx.coroutines.flow.collect

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {
    private lateinit var adapter: NewsRecyclerViewPagingAdapter
    private lateinit var binding: LatestNewsFragmentBinding
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
//        viewModel.list.observe(viewLifecycleOwner) {
//            adapter.submitList(it.articles)
//        }
//
        binding.allNewRecyclerView.apply {
            adapter = this@LatestNewsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getLatestNews()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.newsArticles.collect {
                adapter.submitData(it)
            }
            viewModel.event.collect {
                when (it) {
                    is LatestNewsViewModel.Event.ErrorResponse -> {
                        makeLongToast(it.message)
                        activityViewModel.setNetworkStatus(false)
                        binding.swipeRefresh.isRefreshing = false

                    }
                    LatestNewsViewModel.Event.UnknownError -> {

                        activityViewModel.setNetworkStatus(false)
                        makeShortToast("UnknownError")
                        binding.swipeRefresh.isRefreshing = false

                    }
                    LatestNewsViewModel.Event.Loading -> {
                        activityViewModel.setNetworkStatus(false)
                        binding.swipeRefresh.isRefreshing = true
                    }
                    LatestNewsViewModel.Event.InternetError -> {
                        activityViewModel.setNetworkStatus(true)
                        binding.swipeRefresh.isRefreshing = false
                    }
                    LatestNewsViewModel.Event.NoError -> {
                        activityViewModel.setNetworkStatus(false)
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
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
    }
}