package com.example.provincesofvietnam.presentation.provincelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.provincesofvietnam.ProvinceApplication
import com.example.provincesofvietnam.R
import com.example.provincesofvietnam.adapters.ProvinceAdapter
import com.example.provincesofvietnam.databinding.FragmentOverviewBinding
import com.example.provincesofvietnam.domain.ProvinceDomain

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels {
        OverViewModelFactory((context?.applicationContext as ProvinceApplication).repository)
    }

    private var viewModelAdapter: ProvinceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.provinceList.observe(viewLifecycleOwner,
            Observer<List<ProvinceDomain>> { provinces ->
            provinces?.apply {
                viewModelAdapter?.provincesList = provinces
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModelAdapter = ProvinceAdapter()
        binding.provincesGrid.adapter = viewModelAdapter

        return binding.root
    }
}
