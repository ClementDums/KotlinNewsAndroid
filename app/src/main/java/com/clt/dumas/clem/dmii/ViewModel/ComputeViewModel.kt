package com.clt.dumas.clem.dmii.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ComputeViewModel: ViewModel() {

    private val _resultatLiveData = MutableLiveData<Double>()
    val resultatLiveData: LiveData<Double>
        get() = _resultatLiveData

    fun returnSum(nb1:Double, nb2:Double){
        _resultatLiveData.value = nb1.plus(nb2)
    }
    fun returnMinus(nb1:Double, nb2:Double){
        _resultatLiveData.value = nb1.minus(nb2)
    }
    fun returnProduct(nb1:Double, nb2:Double){
        _resultatLiveData.value = nb1.times(nb2)
    }
    fun returnDiv(nb1:Double, nb2:Double){
        _resultatLiveData.value = nb1.div(nb2)
    }
}