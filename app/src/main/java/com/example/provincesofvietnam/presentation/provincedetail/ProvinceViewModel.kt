package com.example.provincesofvietnam.presentation.provincedetail

import android.app.Application
import androidx.lifecycle.*
import com.example.provincesofvietnam.data.ProvinceRoomDatabase.Companion.getDatabase
import com.example.provincesofvietnam.domain.ProvinceDomain
import com.example.provincesofvietnam.repository.ProvinceRepository
import kotlinx.coroutines.launch

class ProvinceViewModel(private val application: Application, private val codeId: Int) : ViewModel() {
    private val _provincesRepository = ProvinceRepository(getDatabase(application).provinceDao)
    val provincesRepository get() = _provincesRepository

//    val provinceList = provincesRepository.provinces

    //private val _provinceRepository = ProvinceRepository()

    private var _codeId : MutableLiveData<Int> = MutableLiveData(0)
    val code: LiveData<Int> = _codeId

    private var _provinceDomain: MutableLiveData<ProvinceDomain> = MutableLiveData()
    val provinceDomain: LiveData<ProvinceDomain> = _provinceDomain

    init {
        _codeId.value = codeId
        // query codeId
        viewModelScope.launch {
            getProvince(codeId)
        }

    }

    private suspend fun getProvince(code: Int) {
        _provinceDomain.value = _provincesRepository.getProvince(code)
    }
}