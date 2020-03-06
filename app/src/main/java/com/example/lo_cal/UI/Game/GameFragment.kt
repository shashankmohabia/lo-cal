package com.example.lo_cal.UI.Game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.lo_cal.R
import com.example.lo_cal.Utils.Extensions.toast
import com.example.lo_cal.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game, container, false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        setObservers()
        setOnClickListeners()

        return binding.root
    }

    private fun setObservers() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            result = viewModel.result.value.toString()
        })

        viewModel.isInputValid.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                toast("Please enter both names")
            } else if (it == true) {
                navigateToResult()
            }
        })
    }

    private fun setOnClickListeners() {
        binding.calculateButton.setOnClickListener {
            viewModel.calculate(
                binding.firstPersonName.text.toString(),
                binding.secondPersonName.text.toString()
            )
        }
    }

    private fun navigateToResult() {
        val action =
            GameFragmentDirections.actionGameFragmentToResultFragment(
                binding.firstPersonName.text.toString(),
                binding.secondPersonName.text.toString(),
                result
            )
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onCalculationComplete()
    }
}


