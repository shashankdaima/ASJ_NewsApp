package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeShortToast
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {

    private lateinit var binding: LatestNewsFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LatestNewsFragmentBinding.bind(view)
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
}