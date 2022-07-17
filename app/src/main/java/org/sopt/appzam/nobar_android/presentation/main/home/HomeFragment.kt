package org.sopt.appzam.nobar_android.presentation.main.home

import android.icu.util.TimeUnit.values
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentHomeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.util.ItemDecoration

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        onRefresh()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerToDoRecipe.addItemDecoration(
            ItemDecoration(
                R.dimen.margin8,
                R.dimen.margin8,
                2
            )
        )
    }

    private fun onRefresh() {
        binding.layoutRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.layoutMain.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.black
                    )
                )
                binding.layoutRefresh.isRefreshing = false
            }, 1000)
        }
    }

}