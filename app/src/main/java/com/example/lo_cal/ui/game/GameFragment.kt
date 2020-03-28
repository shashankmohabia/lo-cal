package com.example.lo_cal.ui.game

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.lo_cal.R
import com.example.lo_cal.utils.extensions.toast
import com.example.lo_cal.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(GameViewModel::class.java)
    }
    private lateinit var result: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater).apply {
            gameViewModel = viewModel
        }

        setObservers()

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


