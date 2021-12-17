package com.example.androidstudyjam1.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.WebviewFragmentBinding

class WebviewFragment : Fragment(R.layout.webview_fragment) {
    private val args by navArgs<WebviewFragmentArgs>()
    private lateinit var binding: WebviewFragmentBinding
    private val viewModel by activityViewModels<ActivityViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WebviewFragmentBinding.bind(view)
        val article =args.article
        binding.webView.apply{
            webViewClient= WebViewClient()
            loadUrl(article.url)
            settings.cacheMode=WebSettings.LOAD_DEFAULT
        }
        (activity as MainActivity).supportActionBar?.title=article.title
        (activity as MainActivity).supportActionBar?.subtitle=binding.webView.url
    }
}