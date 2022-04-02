package org.sopt.mvvmdiaryexample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.domain.Diary
import org.sopt.mvvmdiaryexample.utils.SingleLiveEvent
import java.lang.IllegalStateException
import java.util.*

class EditDiaryViewModel : ViewModel() {
    // 제목, 내용, 날짜
    private val _createDate = MutableLiveData(Date())
    val title = MutableLiveData<String>()   // two-way DataBinding 위해 _title(x)
    val content = MutableLiveData<String>()
    val createDate: LiveData<Date> = _createDate
    val editSuccessEvent = SingleLiveEvent<Unit>()

    fun loadDiary(diaryId: String?) {
        val diary = DiaryMemory.getDiary(diaryId ?: return) /* null 이면 return 하겠다 */
        title.value = diary.title
        content.value = diary.content
        _createDate.value = diary.createDate
    }

    fun saveDiary() {
        val title = this.title.value.orEmpty()
        val content = this.content.value.orEmpty()
        val createDate =
            this.createDate.value ?: throw IllegalStateException("Create Date Cannot Be Null")
        // val createDate = this.createDate.value ?: Date() /*예외처리 하기 나름*/

        val diary = Diary(UUID.randomUUID().toString(), title, content, createDate)
        DiaryMemory.saveDiary(diary)
        editSuccessEvent.value = Unit
    }
}