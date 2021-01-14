package com.mrmobo.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrmobo.conduit.R
import com.mrmobo.conduit.databinding.FragmentFeedBinding

class MyFeedFragment : Fragment() {

    private  var _binding: FragmentFeedBinding? =null
    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedAdapter = ArticleFeedAdapter { openArticle(it) }
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
//        _binding?.fetchFeedButton?.setOnClickListener {
        _binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        _binding?.feedRecyclerView?.adapter = feedAdapter

        //    }
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyFeed()
        viewModel.feed.observe({lifecycle}){
            feedAdapter.submitList(it)
        }
       // viewModel.fetchGlobalFeed()
    }

    fun openArticle(articleId : String){
        findNavController().navigate(R.id.action_globalFeed_openArticle,
            bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}