package org.sopt.mvvmdiaryexample.presentation

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.databinding.ActivityEditDiaryBinding
import org.sopt.mvvmdiaryexample.domain.Diary
import java.util.*

class EditDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDiaryBinding
    private val editDiaryViewModel: EditDiaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = EditDiaryViewModel()
        setContentView(binding.root)

        editDiaryViewModel.loadDiary(getDiaryId())

        binding.bindingString = "제목"
        binding.bindingInteger = 123

        editDiaryViewModel.editSuccessEvent.observe(this) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private fun getDiaryId(): String? {
        return intent.getStringExtra(KEY_DIARY)
    }

    companion object {
        const val KEY_DIARY = "KEY_DIARY"
    }
}