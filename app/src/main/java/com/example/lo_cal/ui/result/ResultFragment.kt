package com.example.lo_cal.ui.result

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentResultBinding
import com.example.lo_cal.utils.extensions.getTextShareIntent

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory

    private val application by lazy {
        requireNotNull(this.activity).application
    }

    private val args: ResultFragmentArgs by lazy {
        ResultFragmentArgs.fromBundle(arguments!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)

        initializeViewModel()
        initializeBindingVariables()
        setHasOptionsMenu(true)
        setObservers()

        return binding.root
    }

    private fun initializeViewModel() {
        viewModelFactory = ResultViewModelFactory(args, application)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
    }

    private fun initializeBindingVariables() {
        binding.apply {
            resultViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun setObservers() {
        viewModel.calculateAgain.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                navigateToGame()
                viewModel.onCalculateAgainComplete()
            }
        })
    }

    private fun navigateToGame() {
        NavHostFragment.findNavController(this).navigate(R.id.action_resultFragment_to_gameFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.result_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shareResult) {
            getTextShareIntent("text/plain", viewModel.currentEntry.result)
            return true
        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)

    }
}

