package com.example.provincesofvietnam.presentation.provincelist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.provincesofvietnam.ProvinceApplication
import com.example.provincesofvietnam.R
import com.example.provincesofvietnam.adapters.ProvinceAdapter
import com.example.provincesofvietnam.databinding.FragmentOverviewBinding
import com.example.provincesofvietnam.domain.ProvinceDomain

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    private val viewModel: OverviewViewModel by viewModels {
        OverViewModelFactory((context?.applicationContext as ProvinceApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_overview,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigateToSelectedProperty.observe(this.viewLifecycleOwner) {
            it?.let {
                navigateToProvinceDetail(it)
                viewModel.displayPropertyDetailsComplete()
            }
        }
        val viewModelAdapter = ProvinceAdapter(
            ProvinceAdapter.OnClickListener(
                clickListener = { province ->
                    viewModel.displayPropertyDetails(province)
                }
            )
        )

        viewModel.provinceList.observe(this.viewLifecycleOwner, Observer<List<ProvinceDomain>> { province ->
            province?.apply {
                viewModelAdapter.submitList(province)
            }
        })

        binding.provincesGrid.adapter = viewModelAdapter
    }


    private fun navigateToProvinceDetail(provinceDomain: ProvinceDomain) {
        val direction = OverviewFragmentDirections.actionOverviewFragmentToProvinceFragment(
            codeId = provinceDomain.code,
            provinceName = provinceDomain.name
        )
        findNavController().navigate(direction)
    }
}
