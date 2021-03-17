package com.bravedroid.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bravedroid.app.R
import com.bravedroid.app.databinding.FragmentMasterBinding
import com.bravedroid.app.databinding.FragmentMasterLandBinding

class MasterFragment : Fragment() {
    private val isTablet: Boolean
        get() = requireContext().resources.getBoolean(R.bool.isTablet)

    private lateinit var binding: FragmentMasterBinding
    private lateinit var landBinding: FragmentMasterLandBinding

    private val activityViewModel: MainActivityViewModel by activityViewModels {
        viewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = if (isTablet) {
        landBinding = FragmentMasterLandBinding.inflate(inflater, container, false)
        landBinding.root
    } else {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        binding.root
    }

    override fun onStart() {
        super.onStart()
        activityViewModel.loadData()
        if (isTablet)
            setupMasterDetailLayout()
        else
            setupSingleLayout()
    }

    private fun setupSingleLayout() {
        val itemAdapter = ItemAdapter()
        binding.itemRecyclerView.adapter = itemAdapter

        activityViewModel.items.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)
        }
    }

    private fun setupMasterDetailLayout() {
        val itemAdapter = ItemAdapter()
        landBinding.itemRecyclerView.adapter = itemAdapter
        activityViewModel.items.observe(viewLifecycleOwner) {
            itemAdapter.submitList(it)
        }
    }
}
