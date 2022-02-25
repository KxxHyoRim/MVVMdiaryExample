package org.sopt.mvvmdiaryexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.mvvmdiaryexample.databinding.ActivityEditDiaryBinding

class EditDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        const val KEY_DIARY = "KEY_DIARY"
    }
}