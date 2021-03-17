package com.bravedroid.app.presentation


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.app.R
import com.bravedroid.app.databinding.FragmentMasterBinding
import com.bravedroid.app.databinding.FragmentMasterLandBinding
import com.bravedroid.app.presentation.Util.requireItemApplication

class MasterFragment : Fragment(), LifecycleObserver {
    private val isTablet: Boolean
        get() = requireContext().resources.getBoolean(R.bool.isTablet)

    private lateinit var binding: FragmentMasterBinding
    private lateinit var landBinding: FragmentMasterLandBinding

    private lateinit var activityViewModel: MainActivityViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(this)
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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onActivityCreated() {
        injectViewModel()
        requireActivity().lifecycle.removeObserver(this)
    }

    private fun injectViewModel() {
        val factory: ViewModelProvider.Factory = ViewModelFactory(requireItemApplication())
        activityViewModel = ViewModelProvider(
            requireActivity(),
            factory,
        )[MainActivityViewModel::class.java]
    }
}
