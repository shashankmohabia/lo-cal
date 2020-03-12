package com.example.lo_cal.ui.result

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentResultBinding
import com.example.lo_cal.utils.extensions.toast

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var args: ResultFragmentArgs
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var resultViewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = ResultFragmentArgs.fromBundle(arguments!!)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )

        resultViewModelFactory = ResultViewModelFactory(
            args.firstPersonName,
            args.secondPersonName,
            args.result
        )

        resultViewModel =
            ViewModelProvider(this, resultViewModelFactory).get(ResultViewModel::class.java)

        binding.resultViewModel = resultViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)
        setObservers()

        return binding.root
    }

    private fun setObservers() {
        resultViewModel.calculateAgain.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                navigateToGame()
                resultViewModel.onCalculateAgainComplete()
            }
        })
    }

    private fun navigateToGame() {
        NavHostFragment.findNavController(this).navigate(R.id.action_resultFragment_to_gameFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shareResult) {
            toast("Share Result")
            return true
        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)

    }
}
