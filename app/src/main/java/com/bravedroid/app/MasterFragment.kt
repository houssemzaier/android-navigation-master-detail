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
import androidx.navigation.Navigation.createNavigateOnClickListener
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bravedroid.app.databinding.FragmentMasterBinding
import com.bravedroid.app.databinding.FragmentMasterLandBinding

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
        if (isTablet)
            setupNavigationForMasterDetailLayout()
        else
            setupNavigationForSingleLayout()
    }

    private fun setupNavigationForSingleLayout() {
        /**
        /*sample: navigate to an action, a destination,  NavDirections*/
        binding.accountTextview.setOnClickListener {
        findNavController().navigate(R.id.action_master_fragment_to_fragment_detail)
        findNavController().navigate(R.id.fragment_detail)
        findNavController().navigate(MasterFragmentDirections.actionMasterFragmentToFragmentDetail())
        }
        /*sample: createNavigateOnClickListener extension*/
        binding.accountTextview.setOnClickListener(
        createNavigateOnClickListener(
        MasterFragmentDirections.actionMasterFragmentToFragmentDetail()
        )
        )
         **/

        binding.accountTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("account")
            findNavController().navigate(MasterFragmentDirections.actionMasterFragmentToFragmentDetail())
        }
        binding.notificationsTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("notifications")
            findNavController().navigate(MasterFragmentDirections.actionMasterFragmentToFragmentDetail())
        }
        binding.settingsTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("settings")
            findNavController().navigate(MasterFragmentDirections.actionMasterFragmentToFragmentDetail())
        }
    }

    private fun setupNavigationForMasterDetailLayout() {
        /*sample: getting a secondary navController*/
        val childNavController1 =
            (childFragmentManager.findFragmentById(R.id.secondary_container) as NavHostFragment).navController
        val childNavController2 =
            (childFragmentManager.findFragmentByTag("secondary_container") as NavHostFragment).navController
        val findNavController31 = landBinding.secondaryContainer.findNavController()

        /*sample: getting a main navController*/
        val navController1 =
            (requireActivity().supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment).navController
        val navController2 =
            (requireActivity().supportFragmentManager.findFragmentByTag("main_container") as NavHostFragment).navController

        val navController30 = findNavController()
        var navController31 = requireView().findNavController()
        var navController32 = requireActivity().findNavController(R.id.main_container)

        landBinding.accountTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("account")
        }

        landBinding.notificationsTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("notifications")
        }

        landBinding.settingsTextview.setOnClickListener {
            activityViewModel.currentSelectedItem("settings")
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
