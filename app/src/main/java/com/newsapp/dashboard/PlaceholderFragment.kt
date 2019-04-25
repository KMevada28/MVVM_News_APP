package com.newsapp.dashboard

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newsapp.R
import com.newsapp.databinding.FragmentMainBinding
import com.newsapp.detail.DetailActivity
import com.newsapp.utils.Constants.Companion.ARG_ARTICLE_DATA
import com.newsapp.utils.Constants.Companion.TYPE_NOT_CONNECTED
import com.newsapp.utils.NetworkState
import com.newsapp.utils.Constants.Companion.showNetworkErrorScreen


/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment(), OnRetryClickListener {


    private lateinit var placeholderViewModel: PlaceholderViewModel
    private lateinit var country: String
    private lateinit var category: String
    lateinit var rootView: View
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        rootView = binding.getRoot()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this.rootView.context, LinearLayoutManager.VERTICAL, false)
        category = arguments?.getString(ARG_CATEGORY)!!
        country = arguments?.getString(ARG_COUNTRY)!!
        placeholderViewModel = ViewModelProviders.of(this).get(PlaceholderViewModel::class.java)
        getData(country, category)
        binding.viewModel = placeholderViewModel
        placeholderViewModel.postListAdapter?.onItemClick = { articleResponseEntity ->
            val intent = Intent(this.context, DetailActivity::class.java)
            intent.putExtra(ARG_ARTICLE_DATA, articleResponseEntity)
            startActivity(intent)
        }
        return rootView
    }

    private fun getData(country: String, category: String) {
        if (NetworkState.getConnectivityStatus(this.context) != TYPE_NOT_CONNECTED) {
            placeholderViewModel.getNews(country, category)
        } else {
            showNetworkErrorScreen(fragmentManager,this)
        }
    }

    companion object {
        private val ARG_COUNTRY = "country"
        private val ARG_CATEGORY = "category"

        fun newInstance(country: String, category: String): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putString(ARG_COUNTRY, country)
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onRetryClick() {
        getData(country, category)
    }

}