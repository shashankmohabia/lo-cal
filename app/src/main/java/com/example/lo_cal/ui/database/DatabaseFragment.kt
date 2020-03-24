package com.example.lo_cal.ui.database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentDatabaseBinding
import com.example.lo_cal.utils.extensions.getTextShareIntent
import com.example.lo_cal.utils.extensions.toast

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

        val adapter = DataListAdapter(ItemClickListener {
            toast(it.toString())
            viewModel.onClickShareDetails(it)
        })

        binding.dataList.adapter = adapter
        binding.dataList.layoutManager = GridLayoutManager(activity, 3)
        //binding.dataList.layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)

        setObservers(adapter)

        return binding.root
    }

    private fun setObservers(adapter: DataListAdapter) {
        viewModel.entryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        viewModel.onClickShare.observe(viewLifecycleOwner, Observer {
            it?.let {
                getTextShareIntent("text/plain", it.toString())
                viewModel.onSharingComplete()
            }
        })
    }
}
