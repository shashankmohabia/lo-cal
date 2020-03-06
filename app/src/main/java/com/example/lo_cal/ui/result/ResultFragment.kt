package com.example.lo_cal.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentResultBinding

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

        setOnCLickListeners()
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

    private fun setOnCLickListeners() {
        binding.calculateAgainButton.setOnClickListener {
            resultViewModel.onCalculateAgain()
        }
    }
}
