package com.example.androidstudyjam1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidstudyjam1.databinding.LatestNewsFragmentBinding

class LatestNewsFragment : Fragment(R.layout.latest_news_fragment) {

    private lateinit var binding: LatestNewsFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= LatestNewsFragmentBinding.bind(view)

    }
}