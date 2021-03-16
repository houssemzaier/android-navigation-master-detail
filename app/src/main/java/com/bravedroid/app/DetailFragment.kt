package com.bravedroid.app

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
import com.bravedroid.app.databinding.FragmentDetailBinding

class DetailFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var activityViewModel: MainActivityViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onStart() {
        super.onStart()
        activityViewModel.currentSelectedItem.observe(viewLifecycleOwner) {
            binding.detailTextView.text =it
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onActivityCreated() {
        injectViewModel()
        requireActivity().lifecycle.removeObserver(this)
    }

    private fun injectViewModel() {
        val factory: ViewModelProvider.Factory = ViewModelFactory()
        activityViewModel = ViewModelProvider(
            requireActivity(),
            factory,
        )[MainActivityViewModel::class.java]
    }
}
