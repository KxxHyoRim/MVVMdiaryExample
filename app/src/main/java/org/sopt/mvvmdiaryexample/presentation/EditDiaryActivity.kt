package org.sopt.mvvmdiaryexample.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.databinding.ActivityEditDiaryBinding
import org.sopt.mvvmdiaryexample.domain.Diary
import java.util.*

class EditDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDiary(getDiaryId())

        val diary = Diary(id = "", title = "제목", content = "내용", createDate = Date()/*현재시간*/)

        /**DataBinding을 쓸거면 아래와 같이 사용*/
        // 방법 1
//        binding.memo = diary
        binding.bindingString = "제목DataBinding"
        binding.bindingInteger = 123456789  /* Int형은 xml에서 android:text="@{String.valueOf()} 처리*/

        // 방법 2
        // binding.setVariable(BR.diary, diary)

        binding.buttonSubmit.setOnClickListener {
            saveDiary()
            setResult(Activity.RESULT_OK)
            finish()
        }
        Log.d("EditDiaryActivity", "Diary Id = ${getDiaryId()}")
    }

    private fun loadDiary(diaryId : String?){
        val diary = DiaryMemory.getDiary(diaryId ?: return) /* null 이면 return 하겠다 */
        binding.memo = diary
    }

    private fun getDiaryId() : String? {
        return intent.getStringExtra(KEY_DIARY)
    }

    private fun saveDiary(){
        val diary = Diary(UUID.randomUUID().toString(),binding.etTitle.toString(), binding.etContents.toString(), Date()        )
        DiaryMemory.saveDiary(diary)
    }

    companion object {
        const val KEY_DIARY = "KEY_DIARY"
    }
}