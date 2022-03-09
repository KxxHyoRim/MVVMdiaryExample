package org.sopt.mvvmdiaryexample.presentation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.mvvmdiaryexample.databinding.ActivityEditDiaryBinding
import org.sopt.mvvmdiaryexample.domain.Diary
import java.util.*

class EditDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diary = Diary(id = "", title = "제목", content = "내용", createDate = Date()/*현재시간*/)
//        val diary2 = Diary( "", "제목", "내용",Date()/*현재시간*/)
//        둘 다 같은 표현인데, 위에는 명시적, 아래는 암시적이라고 하는 듯 하다

        /**ViewBinding을 쓸거면 아래와 같이 사용
         *
         * binding.tvDate.text = diary.createDate.toString()
         * binding.tvTitle.text = diary.title
         * */

        /**DataBinding을 쓸거면 아래와 같이 사용*/
        // 방법 1
        binding.memo = diary
        binding.bindingString = "제목DataBinding"
        binding.bindingInteger = 123456789  /* Int형은 xml에서 android:text="@{String.valueOf()} 처리*/

        // 방법 2
//        binding.setVariable(BR.diary, diary)

        binding.buttonSubmit.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
        Log.d("EditDiaryActivity", "Diary Id = ${getDiaryId()}")
    }

    private fun getDiaryId() : String? {
        return intent.getStringExtra(KEY_DIARY)
    }

    companion object {
        const val KEY_DIARY = "KEY_DIARY"
    }
}