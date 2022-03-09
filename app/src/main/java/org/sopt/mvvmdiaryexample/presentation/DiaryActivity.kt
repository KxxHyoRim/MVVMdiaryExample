package org.sopt.mvvmdiaryexample.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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

        initView()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listDiaries.adapter = DiaryAdapter(::onMemoClick).also { diariesAdapter = it }
        /**위 코드는 아래 두 줄을 동시에 실행하는 의미를 가진다
         * diariesAdapter = DiaryAdapter { onMemoClick(it) }
         * binding.listDiaries.adapter = diariesAdapter */

        /** 또한, 아래 코드는 동일한 코드이다
         *  1) binding.listDiaries.adapter = DiaryAdapter(::onMemoClick)
         *  2) binding.listDiaries.adapter = DiaryAdapter({onMemoClick(it)})
         *  3) binding.listDiaries.adapter = DiaryAdapter{onMemoClick(it)}  // convention
         * */

        binding.buttonNewDiary.setOnClickListener { deployEditDiaryActivity() }

        diariesAdapter.submitList(STUB_DIARIES) {
            // ctrl + p
            // submitList 가 비동기라, 이 작업이 끝나고 실행시키고 싶은 코드를
            // 이 Scope 에 작성하면 됨
        }
    }

    private fun onMemoClick(diary: Diary) = deployEditDiaryActivity(diary)

    private fun deployEditDiaryActivity(diary: Diary? = null) {
        val intent = Intent(this, EditDiaryActivity::class.java).apply {
            putExtra(EditDiaryActivity.KEY_DIARY, diary?.id)
        }
        editDiaryActivityLauncher.launch(intent)
    }

    companion object {
        private val STUB_DIARIES = listOf(
            Diary("0", "제목", "내용", Date()),
            Diary("1", "제목", "내용", Date()),
            Diary("2", "제목", "내용", Date()),
            Diary("3", "제목", "내용", Date()),
            Diary("4", "제목", "내용", Date()),
        )
    }
}