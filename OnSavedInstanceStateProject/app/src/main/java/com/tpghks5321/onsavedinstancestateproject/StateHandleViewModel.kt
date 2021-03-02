package com.tpghks5321.onsavedinstancestateproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StateHandleViewModel @Inject internal constructor(private val handle: SavedStateHandle) :
    ViewModel() {

    private val NAME_KEY = "name"

    fun getName(): LiveData<String> {
        return handle.getLiveData(NAME_KEY)
    }

    fun saveNewName(newName: String) {
        handle[NAME_KEY] = newName
    }

}