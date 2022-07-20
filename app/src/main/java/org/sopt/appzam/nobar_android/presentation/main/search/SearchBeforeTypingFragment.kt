package org.sopt.appzam.nobar_android.presentation.main.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentSearchBeforeTypingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchSuggestAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchDetailViewModel

class SearchBeforeTypingFragment :
    BaseFragment<FragmentSearchBeforeTypingBinding>(R.layout.fragment_search_before_typing) {
    private val searchDetailViewModel: SearchDetailViewModel by activityViewModels()
    private lateinit var searchSuggestAdapter: SearchSuggestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observingRecommendsData()
    }

    private fun initAdapter() {
        searchSuggestAdapter = SearchSuggestAdapter()
        binding.recyclerSuggest.adapter = searchSuggestAdapter
    }

    private fun observingRecommendsData() {
        searchDetailViewModel.searchKeyRecommends.observe(viewLifecycleOwner) {
            searchSuggestAdapter.submitList(it)
        }
    }
}