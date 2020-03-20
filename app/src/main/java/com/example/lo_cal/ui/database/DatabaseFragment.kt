package com.example.lo_cal.ui.database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {

    private lateinit var binding: FragmentDatabaseBinding
    private lateinit var viewModel: DatabaseViewModel
    private lateinit var viewModelFactory: DatabaseViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_database,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        viewModelFactory = DatabaseViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DatabaseViewModel::class.java)

        binding.databaseViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}
