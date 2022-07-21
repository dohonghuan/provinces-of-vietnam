package com.example.provincesofvietnam.presentation.provincelist

import android.util.Log
import androidx.lifecycle.*
import com.example.provincesofvietnam.domain.ProvinceDomain
import com.example.provincesofvietnam.network.ProvinceProperty
import com.example.provincesofvietnam.repository.ProvinceRepository
import kotlinx.coroutines.launch
import java.io.IOException

class OverviewViewModel(private val repository: ProvinceRepository): ViewModel() {

    val provinceList = repository.provinces

    private val _navigateToSelectedProperty = MutableLiveData<ProvinceDomain?>()
    val navigateToSelectedProperty: LiveData<ProvinceDomain?>
        get() = _navigateToSelectedProperty

    init {
        getProvinceProperties()
    }

    private fun getProvinceProperties() {
        viewModelScope.launch {
            try {
                repository.refreshProvinces()
            } catch (networkError: IOException) {
                Log.d("POV", "networkError")
            }
        }
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun displayPropertyDetails(province: ProvinceDomain) {
        _navigateToSelectedProperty.value = province
    }
}

class OverViewModelFactory(private val repository: ProvinceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OverviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}