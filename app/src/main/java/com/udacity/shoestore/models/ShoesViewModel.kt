package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoesViewModel : ViewModel() {
    private var _shoeObject = MutableLiveData<ArrayList<Shoe>>()
    val shoeObjects: LiveData<ArrayList<Shoe>>
        get() = _shoeObject
    private var _fragmentFinished = MutableLiveData<Boolean>()
    val fragmentFinished: LiveData<Boolean>
        get() = _fragmentFinished
    var shoeItem: Shoe

    init {
        _shoeObject.value = ArrayList()
        _fragmentFinished.value = false
        shoeItem = Shoe("", 0.0, "", "")
    }

    fun onSaveShoe(shoe: Shoe) {
        _shoeObject.value?.add(shoe)
        _fragmentFinished.value = true
        shoeItem = Shoe("", 0.0, "", "")
    }

    fun onFinished() {
        _fragmentFinished.value = false
    }
}