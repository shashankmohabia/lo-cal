package com.example.lo_cal.UI.Game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.lo_cal.UI.Game.GameFragmentDirections
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

        viewModel.result.observe(viewLifecycleOwner, Observer {
            result = viewModel.result.value.toString()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateButton.setOnClickListener {
            if (binding.firstPersonName.text.toString() == "" || binding.secondPersonName.text.toString() == "") {
                toast("Can't calculate for one person")
            } else {
                viewModel.getResult()
                val action =
                    GameFragmentDirections.actionGameFragmentToResultFragment(
                        binding.firstPersonName.text.toString(),
                        binding.secondPersonName.text.toString(),
                        result
                    )
                view.findNavController().navigate(action)
            }
        }
    }
}
