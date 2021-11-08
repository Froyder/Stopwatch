package com.example.stopwatch.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.databinding.ActivitySearchBinding
import com.example.stopwatch.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySearchBinding

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        searchViewModel.searchLiveData.observe(this) { viewBinding.resultTextView.text = it }

        viewBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchViewModel.setQuery(it) }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchViewModel.setChangedQuery(newText)
                return true
            }
        })
    }
}