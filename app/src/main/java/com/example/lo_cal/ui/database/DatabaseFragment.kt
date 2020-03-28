package com.example.lo_cal.ui.database

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lo_cal.R
import com.example.lo_cal.databinding.FragmentDatabaseBinding
import com.example.lo_cal.utils.extensions.getTextShareIntent
import com.example.lo_cal.utils.extensions.toast

class DatabaseFragment : Fragment() {

    private lateinit var binding: FragmentDatabaseBinding
    private lateinit var viewModel: DatabaseViewModel
    private lateinit var viewModelFactory: DatabaseViewModelFactory
    private lateinit var recyclerViewAdapter: DataListAdapter
    private val application by lazy {
        requireNotNull(this.activity).application
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatabaseBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        initializeViewModel()
        setUpRecyclerView()
        setObservers()

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun initializeViewModel() {
        viewModelFactory = DatabaseViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DatabaseViewModel::class.java)
    }

    private fun setUpRecyclerView() {
        recyclerViewAdapter = DataListAdapter(ItemClickListener {
            toast(it.toString())
            viewModel.onClickShareDetails(it)
        })

        binding.dataList.apply {
            adapter = recyclerViewAdapter
            layoutManager = GridLayoutManager(activity, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (position) {
                            0 -> 3
                            else -> 1
                        }
                    }
                }
            }
        }
        //binding.dataList.layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)

    }

    private fun setObservers() {
        viewModel.entryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerViewAdapter.addHeaderAndSubmitList(it)
            }
        })

        viewModel.onClickShare.observe(viewLifecycleOwner, Observer {
            it?.let {
                getTextShareIntent("text/plain", it.toString())
                viewModel.onSharingComplete()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.database_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.clean_db_option) {
            viewModel.onClean()
            return true
        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
