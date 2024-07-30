package com.abc.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.abc.app.adapters.CarouselAdapter
import com.abc.app.adapters.DataListAdapter
import com.abc.app.databinding.FragmentListBinding
import com.abc.app.viewmodals.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ListFragment : Fragment() {
    private val binding by lazy { FragmentListBinding.inflate(layoutInflater) }
    private val viewModel: DataViewModel by viewModels()
    private val recordListAdapter by lazy {
        DataListAdapter { isEmpty ->
            binding.tvNoResultFound.visibility = if (isEmpty) View.VISIBLE else View.GONE
        }
    }
    private val carouselAdapter by lazy { CarouselAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclerView()
        setupCarousel()
        subscribeUi()
    }

    private fun setupListeners() = with(binding) {
        etFilter.addTextChangedListener { editable ->
            viewModel.filterListData(editable.toString())
        }

        fab.setOnClickListener { showBottomSheet() }
    }

    private fun showBottomSheet() {
        StatsBottomSheetDialog(viewModel.filteredListData.value).show(
            parentFragmentManager,
            "ModalBottomSheet"
        )
    }

    private fun setupRecyclerView() = with(binding.rvRecords) {
        layoutManager = LinearLayoutManager(context)
        adapter = recordListAdapter
    }

    private fun subscribeUi() = viewLifecycleOwner.lifecycleScope.launch {
        launch {
            viewModel.filteredListData.collect { recordListAdapter.publishData(it) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filteredListData.collect { filteredList ->
                recordListAdapter.submitList(filteredList)
            }
        }
        launch {
            viewModel.carousalList.collect { carouselAdapter.submitList(it) }
        }
    }

    private fun setupCarousel() = with(binding) {
        vpImageCarousel.adapter = carouselAdapter
        TabLayoutMediator(tlIndicator, vpImageCarousel) { _, _ -> }.attach()
        vpImageCarousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                etFilter.setText("")
                viewModel.getData(position)
            }
        })
    }
}