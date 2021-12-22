package com.example.androidstudyjam1.ui.saved_news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.FragmentSavedNewsBinding
import com.example.androidstudyjam1.ui.adapters.NewsRecyclerViewAdapter
import kotlinx.coroutines.flow.collect

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {
    private lateinit var binding: FragmentSavedNewsBinding
    private val viewModel by viewModels<SavedNewsViewModel> {
        SavedMessageViewModelFactory(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        val adapter = NewsRecyclerViewAdapter {
            findNavController().navigate(
                SavedNewsFragmentDirections.actionSavedNewsFragmentToWebviewFragment(
                    it, isArticleSaved = true
                )
            )
        }
        binding.allNewsRecyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.listOfSavedNews.collect {
                adapter.submitList(it)
            }
        }
    }
}