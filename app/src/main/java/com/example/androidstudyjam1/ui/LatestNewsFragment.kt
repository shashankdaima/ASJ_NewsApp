package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {

    private lateinit var binding: LatestNewsFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)
        setHasOptionsMenu(true)
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