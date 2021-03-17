package com.bravedroid.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bravedroid.app.databinding.FragmentDetailBinding
import timber.log.Timber

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val activityViewModel: MainActivityViewModel by activityViewModels{
          viewModelFactory()
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
            binding.detailTextView.text = it
        }
    }
}
