package org.sopt.mvvmdiaryexample.presentation

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.mvvmdiaryexample.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLiveDataBinding
    private val liveDataTest = LiveDataTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        binding.livedataTest = liveDataTest
        binding.lifecycleOwner = this

        setContentView(binding.root)

//        CoroutineScope(Dispatchers.IO).launch {
//            liveDataTest.stateFlowEx.collect {
//            }
//        }

        liveDataTest.count.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                sender as ObservableInt
                sender.get()    // count 가져옴
                Log.d(TAG, "onPropertyChanged: $sender.get()")
            }
        })

//        liveDataTest.count.observe(this){
//            Log.d("LiveDataActivity", "count = $it")
//        }
    }
}