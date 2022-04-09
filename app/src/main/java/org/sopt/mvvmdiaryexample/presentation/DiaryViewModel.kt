package org.sopt.mvvmdiaryexample.presentation

import android.app.Application
import androidx.lifecycle.*
import org.sopt.mvvmdiaryexample.data.DiaryMemory
import org.sopt.mvvmdiaryexample.domain.Diary
import org.sopt.mvvmdiaryexample.data.dao.DiaryDao
import org.sopt.mvvmdiaryexample.data.db.DailyDiaryDatabase
import org.sopt.mvvmdiaryexample.data.entity.DiaryEntity

class DiaryViewModel(application: Application) : AndroidViewModel(application) {
    // by ViewModel에서 알아서 해줘서 뷰모델 팩토리 안써도 됨..

    private val diariesDao: DiaryDao = DailyDiaryDatabase.getInstance(application).getDiariesDao()

    private val _diaries = MutableLiveData<List<Diary>>()
    val diaries: LiveData<List<Diary>> = diariesDao.getAllDiaries().map { diaryEntities : List<DiaryEntity> ->
        diaryEntities.map{
            Diary(
                id = it.id, title = it.title, content = it.content, createDate = it.createdDate
            )
        }
    }

}