package com.example.provincesofvietnam.presentation.provincedetail

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.provincesofvietnam.databinding.FragmentProvinceBinding

class ProvinceFragment : Fragment() {

    private var codeId: Int = 0
    private lateinit var provinceName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            codeId = it.getInt("codeId")
            provinceName = it.getString("provinceName").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProvinceBinding.inflate(
            inflater,
            container,
            false
        )
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(
            this,
            ProvinceViewModelFactory(context?.applicationContext as Application,codeId)
        )[ProvinceViewModel::class.java]

        binding.viewModel = viewModel
        viewModel.code.observe(viewLifecycleOwner) {}
        binding.buttonSearchOnGoogle.setOnClickListener {
            val queryUrl: Uri = Uri.parse("$SEARCH_GOOGLE_PREFIX${provinceName}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)

            binding.root.context.startActivity(intent)
        }

        binding.buttonSearchOnMap.setOnClickListener {
            val queryUrl = Uri.parse("$SEARCH_MAP_PREFIX${provinceName}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)

            binding.root.context.startActivity(intent)
        }

        return binding.root
    }

    companion object {
        const val SEARCH_GOOGLE_PREFIX = "https://www.google.com/search?q="
        const val SEARCH_MAP_PREFIX = "geo:0,0?q="
    }
}