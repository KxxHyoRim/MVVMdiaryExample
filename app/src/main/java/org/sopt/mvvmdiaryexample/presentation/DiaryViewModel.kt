package org.sopt.mvvmdiaryexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.domain.Diary
import androidx.lifecycle.ViewModel

class DiaryViewModel : ViewModel() {

    private val _diary = MutableLiveData<List<Diary>>()
    val diary: LiveData<List<Diary>> = _diary

//    init {
//        _diary.value = listOf()
//    }

    fun loadDiaries() {
        _diary.value = DiaryMemory.getAllDiaries()
    }


//    override fun onCleared() {
//        super.onCleared()
//        // do Something
//    }
}