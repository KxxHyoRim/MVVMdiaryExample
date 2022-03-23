package org.sopt.mvvmdiaryexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.mvvmdiaryexample.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLiveDataBinding
    private val liveDataTest = LiveDataTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        binding.livedataTest = liveDataTest
        binding.lifecycleOwner = this

        setContentView(binding.root)


//        liveDataTest.count.observe(this){
//            Log.d("LiveDataActivity", "count = $it")
//        }
    }
}