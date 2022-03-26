package org.sopt.mvvmdiaryexample.presentation

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataTest : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count : LiveData<Int>
        get() = _count  // 내부에서만 변경 가능하다는 의미(kotlin 공식 컨벤션)

    init {
        _count.value = 1
        val count = _count.value    // null 처리 유의

        Thread{
            while(true){
                Thread.sleep(1_000)
                val currentCount = _count.value ?: 0
                /** 방법1 . postValue */
                _count.postValue(currentCount+1)          // liveData 에서 제공

                /** 방법2. MainLooper 에서*/
//                Handler(Looper.getMainLooper()).post{
//                    _count.value = currentCount + 1   // value 변경은 꼭 mainThread 에서 해야한다
//                }
            }
        }.start()
    }

}