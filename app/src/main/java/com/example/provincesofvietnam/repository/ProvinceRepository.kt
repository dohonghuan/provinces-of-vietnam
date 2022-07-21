package com.example.provincesofvietnam.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.provincesofvietnam.data.DatabaseProvince
import com.example.provincesofvietnam.data.ProvinceDao
import com.example.provincesofvietnam.data.asDomainModel
import com.example.provincesofvietnam.domain.ProvinceDomain
import com.example.provincesofvietnam.network.ProvinceApi
import com.example.provincesofvietnam.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProvinceRepository(private val provinceDao: ProvinceDao) {

    // DatabaseProvince => ProvinceDomain
    val provinces: LiveData<List<ProvinceDomain>> =
        Transformations.map(provinceDao.getAllProvinces()) {
            it.asDomainModel()
        }

    suspend fun refreshProvinces() {
        withContext(Dispatchers.IO) {
            val provinceList = ProvinceApi.retrofitService.getAllProvince()
            // ProvinceProperty => DatabaseProvince
            provinceDao.insertAll(provinceList.asDatabaseModel())
        }
    }

    suspend fun getProvince(code: Int): ProvinceDomain {
        val dataProvince: DatabaseProvince
        withContext(Dispatchers.IO) {
            dataProvince = provinceDao.getProvince(code)
        }
        return ProvinceDomain(
            dataProvince.code,
            dataProvince.name,
            dataProvince.divisionType
        )
    }
}