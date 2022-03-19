package org.sopt.mvvmdiaryexample.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.databinding.ActivityDiaryBinding
import org.sopt.mvvmdiaryexample.domain.Diary
import java.util.*

class DiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryBinding
    private lateinit var diariesAdapter: DiaryAdapter
    private lateinit var editDiaryActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 생명주기 주의 : onCreate, onStarted (onResume 전에 호출 해야함)
        editDiaryActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    Activity.RESULT_OK -> showToast("작정 완료!")
                    Activity.RESULT_CANCELED -> showToast("작성 취소.")
                    else -> showToast("문제가 발생했습니다 : $it")
                }
            }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listDiaries.adapter = DiaryAdapter(::onMemoClick).also { diariesAdapter = it }
        binding.buttonNewDiary.setOnClickListener { deployEditDiaryActivity() }

        diariesAdapter.submitList(DiaryMemory.getAllDiaries())

//        diariesAdapter.submitList(STUB_DIARIES) {
//            // ctrl + p
//            // submitList 가 비동기라, 이 작업이 끝나고 실행시키고 싶은 코드를
//            // 이 Scope 에 작성하면 됨
//        }
    }

    private fun onMemoClick(diary: Diary) {
        return deployEditDiaryActivity(diary)
    }

    private fun deployEditDiaryActivity(diary: Diary? = null) {
        val intent = Intent(this, EditDiaryActivity::class.java).apply {
            putExtra(EditDiaryActivity.KEY_DIARY, diary?.id)
        }
        editDiaryActivityLauncher.launch(intent)
    }
}