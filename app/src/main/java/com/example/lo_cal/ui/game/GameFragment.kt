package com.example.lo_cal.ui.game

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
import com.example.lo_cal.utils.extensions.toast
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

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}


