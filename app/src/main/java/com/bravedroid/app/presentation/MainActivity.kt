package com.bravedroid.app.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bravedroid.app.R
import com.bravedroid.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val activityViewModel: MainActivityViewModel by viewModels {
        viewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        navController = findNavController(binding.mainContainer.id)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isFragmentDetailDestination = destination.id == R.id.fragment_detail
            supportActionBar?.apply {
                title = destination.label
                setDisplayHomeAsUpEnabled(isFragmentDetailDestination)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            navController.popBackStack()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}
