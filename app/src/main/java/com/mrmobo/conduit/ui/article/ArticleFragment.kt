package com.mrmobo.conduit.ui.article

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mrmobo.conduit.R
import com.mrmobo.conduit.databinding.FragmentArticleBinding
import com.mrmobo.conduit.extensions.loadImage
import com.mrmobo.conduit.extensions.showFormattedDate

class ArticleFragment : Fragment() {

    private  var _binding: FragmentArticleBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    private var articleId: String? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        _binding = FragmentArticleBinding.inflate(inflater, container, false)


        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
            //Toast.makeText(requireContext(), "Opening article ${articleId}", Toast.LENGTH_SHORT).show()
        }
//        savedInstanceState?.let {
//            articleId = it.getString(resources.getString(R.string.arg_article_id))
//            Toast.makeText(requireContext(), "Opening article ${articleId}", Toast.LENGTH_SHORT).show()
//        }

        articleId?.let {

            articleViewModel.fetchArticle(it)

        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {

            articleViewModel.article.observe({lifecycle}){
                _binding?.apply {
                    titleTextView.text = it.title
                    bodyTextView.text = it.body
                    authorTextView.text = it.author.username
                    dateTextView.showFormattedDate(it.createdAt)
                    avatarImageView.loadImage(it.author.image, true)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}