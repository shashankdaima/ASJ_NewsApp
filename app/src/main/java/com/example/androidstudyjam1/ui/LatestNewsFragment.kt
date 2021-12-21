package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeLongToast
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast
import kotlinx.coroutines.flow.collect

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {
    private lateinit var adapter: NewsRecyclerViewAdapter
    private lateinit var binding: LatestNewsFragmentBinding
    private val viewModel by activityViewModels<ActivityViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.subtitle = null
        adapter = NewsRecyclerViewAdapter {
            findNavController().navigate(
                LatestNewsFragmentDirections.actionLatestNewsFragmentToWebviewFragment(
                    it
                )
            )
        }
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitList(it.articles)
        }
        binding.allNewRecyclerView.apply {
            adapter = this@LatestNewsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getLatestNews()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.event.collect {
                when (it) {
                    is ActivityViewModel.Event.ErrorResponse -> {
                        makeLongToast(it.message)
                        (activity as MainActivity).hideNoInternetRibbon()
                        binding.swipeRefresh.isRefreshing = false

                    }
                    ActivityViewModel.Event.UnknownError -> {
                        (activity as MainActivity).hideNoInternetRibbon()
                        makeShortToast("UnknownError")
                        binding.swipeRefresh.isRefreshing = false

                    }
                    ActivityViewModel.Event.Loading -> {
                        (activity as MainActivity).hideNoInternetRibbon()
                        binding.swipeRefresh.isRefreshing = true
                    }
                    ActivityViewModel.Event.InternetError -> {
                        (activity as MainActivity).showNoInternetRibbon()
                        binding.swipeRefresh.isRefreshing = false
                    }
                    ActivityViewModel.Event.NoError -> {
                        (activity as MainActivity).hideNoInternetRibbon()
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