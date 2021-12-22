package com.example.androidstudyjam1.ui.web_view

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.androidstudyjam1.R
import com.example.androidstudyjam1.databinding.WebviewFragmentBinding
import com.example.androidstudyjam1.local.SavedNewsDao
import com.example.androidstudyjam1.repository.Repository
import com.example.androidstudyjam1.ui.ActivityViewModel
import com.example.androidstudyjam1.ui.MainActivity
import com.example.androidstudyjam1.utils.ToastAndSnackbarExtFunctions.makeLongSnackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WebviewFragment : Fragment(R.layout.webview_fragment) {
    private val args by navArgs<WebviewFragmentArgs>()
    private lateinit var binding: WebviewFragmentBinding
    private lateinit var dao: SavedNewsDao
    private val viewModel by activityViewModels<ActivityViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WebviewFragmentBinding.bind(view)
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
            settings.cacheMode = WebSettings.LOAD_DEFAULT
        }
        (activity as MainActivity).supportActionBar?.title = article.title
        (activity as MainActivity).supportActionBar?.subtitle = binding.webView.url
        dao = Repository.getAllSavedNewsDao(requireContext())
        binding.fabButton.apply {
            if (args.isArticleSaved) {
                setImageDrawable(context.getDrawable(R.drawable.ic_delete))
                setOnClickListener {
                    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                        withContext(Dispatchers.IO) {
                            dao.delete(article)
                        }
                    }
                    makeLongSnackbar("Are you sure?", "UNDO") {
                        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                            withContext(Dispatchers.IO) {
                                dao.add(article)
                            }
                        }
                    }
                }
            } else {
                setImageDrawable(context.getDrawable(R.drawable.baseline_bookmark_24))

                setOnClickListener {
                    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                        withContext(Dispatchers.IO) {
                            dao.add(article)
                        }
                        makeLongSnackbar("Are you sure?", "UNDO") {
                            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                                withContext(Dispatchers.IO) {
                                    dao.delete(article)
                                }
                            }
                        }
                    }
                }
            }


        }
    }
}