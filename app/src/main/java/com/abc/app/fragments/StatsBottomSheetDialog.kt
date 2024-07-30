package com.abc.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.abc.app.adapters.BottomSheetAdapter
import com.abc.app.data.Project
import com.abc.app.databinding.BottomSheetLayoutBinding
import com.abc.app.viewmodals.DataViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StatsBottomSheetDialog(private val filteredList: List<Project>) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetLayoutBinding
    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            val stats = viewModel.getStats(filteredList).await()
            binding.txtTitle.text = "${stats.pageCount} Items"
            val adapter = BottomSheetAdapter(stats)
            binding.bottomSheetRecyclerView.adapter = adapter
        }
    }
}