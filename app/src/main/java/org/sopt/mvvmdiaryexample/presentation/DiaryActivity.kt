package org.sopt.mvvmdiaryexample.presentation

import android.content.Intent
import android.os.Bundle
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

        editDiaryActivityLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //
            }
        initView()
        diariesAdapter.submitList(STUB_DIARIES)
    }

    private fun initView() {
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listDiaries.adapter = DiaryAdapter(::onMemoClick).also { diariesAdapter = it }
        binding.buttonNewDiary.setOnClickListener {
            val intent = Intent(this, EditDiaryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun onMemoClick(diary: Diary) {
        deployEditDiaryActivity(diary)
    }

    private fun deployEditDiaryActivity(diary: Diary? = null) {
        val intent = Intent(this, EditDiaryActivity::class.java)
        if (diary != null) {
            intent.putExtra(EditDiaryActivity.KEY_DIARY, diary.id)
        }
        editDiaryActivityLauncher.launch(intent)
    }

    companion object {
        val STUB_DIARIES = listOf(
            Diary(0, "제목", "내용", Date()),
            Diary(1, "제목", "내용", Date()),
            Diary(2, "제목", "내용", Date()),
            Diary(3, "제목", "내용", Date()),
            Diary(4, "제목", "내용", Date()),
        )
    }
}