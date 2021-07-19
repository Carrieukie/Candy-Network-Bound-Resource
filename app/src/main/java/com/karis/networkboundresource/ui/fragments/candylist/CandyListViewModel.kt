package com.karis.networkboundresource.ui.fragments.candylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karis.networkboundresource.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CandyListViewModel @Inject constructor(mainRepository: MainRepository) : ViewModel(){

    val characters = mainRepository.getCandys().asLiveData()

}