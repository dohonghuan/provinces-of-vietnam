package com.example.provincesofvietnam.presentation.provincedetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProvinceViewModelFactory(private val application: Application, val codeId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProvinceViewModel::class.java)) {
            return ProvinceViewModel(application, codeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}