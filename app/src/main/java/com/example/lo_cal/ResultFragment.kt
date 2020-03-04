package com.example.lo_cal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.example.lo_cal.databinding.FragmentGameBinding
import com.example.lo_cal.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding
    private lateinit var args: ResultFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args = ResultFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(activity, arguments.toString(), Toast.LENGTH_LONG).show()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.result.text = args.firstPersonName

    }

}
