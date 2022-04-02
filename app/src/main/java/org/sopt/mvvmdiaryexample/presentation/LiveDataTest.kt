package org.sopt.mvvmdiaryexample.presentation

import android.os.Handler
import android.os.Looper
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LiveDataTest : ViewModel() {
//    private val _count = MutableLiveData<Int>()
//    val count : LiveData<Int>
//        get() = _count  // 내부에서만 변경 가능하다는 의미(kotlin 공식 컨벤션)

    val count = ObservableInt()
    private val _stateFlowEx = MutableStateFlow(0)
    val stateFlowEx : StateFlow<Int> = _stateFlowEx

    init {
//        _count.value = 1
//        val count = _count.value    // null 처리 유의

        Thread{
            while(true){
                Thread.sleep(1_000)
                _stateFlowEx.value = _stateFlowEx.value + 1

//                val currentCount = _count.value ?: 0
//                count.set(count.get() + 3)
                /** 방법1 . postValue */
//                _count.postValue(currentCount+1)          // liveData 에서 제공

                /** 방법2. MainLooper 에서*/
//                Handler(Looper.getMainLooper()).post{
//                    _count.value = currentCount + 1   // value 변경은 꼭 mainThread 에서 해야한다
//                }
            }
        }.start()
    }

}