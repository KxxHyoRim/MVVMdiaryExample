package org.sopt.mvvmdiaryexample.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.sopt.mvvmdiaryexample.data.entity.DiaryEntity

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diary: DiaryEntity)

    @Query("SELECT * FROM diaryEntity WHERE id = :diaryId")
    fun getDiary(diaryId: String): DiaryEntity

    @Query("SELECT * FROM diaryEntity")
    fun getAllDiaries(): LiveData<List<DiaryEntity>>
    // livedata 는 구독 가능한 형태일 뿐이지, 데이터 그 자체가 아니기 떄문에
    // 처음에 받아왔을 때는 데이터값이 없을 수도 있음
    // room 에서는 mutableLiveData 를 백그라운드에서 만들어서 넘겨주기 떄문에 괜차늠!

    @Update
    fun updateDiary(diary: DiaryEntity)

    @Delete
    fun deleteDiary(diary: DiaryEntity)

    @Query("DELETE FROM diaryEntity WHERE id = :diaryId")
    fun deleteDiary(diaryId: String)
}