package org.sopt.appzam.nobar_android.presentation.main.search

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentSearchAfterTypingBinding
import org.sopt.appzam.nobar_android.databinding.ItemSearchPreviewBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class SearchAfterTypingFragment :
    BaseFragment<FragmentSearchAfterTypingBinding>(R.layout.fragment_search_after_typing) {
    private val searchDetailViewModel: SearchDetailViewModel by activityViewModels()
    private lateinit var adapter: SearchAfterAdapter
    private lateinit var dummyList: ArrayList<RecipeResponse>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = searchDetailViewModel
        initAdapter()
        initDummy()
        observingWord()
    }

    private fun initAdapter() {
        adapter = SearchAfterAdapter()
        binding.recyclerPreview.adapter = adapter
    }

    private fun observingWord() {
        searchDetailViewModel.searchingWord.observe(viewLifecycleOwner) {
            searching(it)
        }
    }

    private fun searching(text: String) {
        val tmpList = ArrayList<RecipeResponse>()
        for (i in 0..dummyList.size - 1) {
            if (dummyList.get(i).name.contains(text)) {
                tmpList.add(dummyList.get(i))
            }
        }
        adapter.findText=text
        adapter.submitList(tmpList)
    }

    private fun initDummy() {
        dummyList = ArrayList<RecipeResponse>()
        dummyList.addAll(
            listOf(
                RecipeResponse("0", "피치 크러시"),
                RecipeResponse("1", "크피치 러시"),
                RecipeResponse("2", "크러피 치시"),
                RecipeResponse("3", "크러시피치"),
                RecipeResponse("3", "응 아니야"),
            )
        )
    }
}