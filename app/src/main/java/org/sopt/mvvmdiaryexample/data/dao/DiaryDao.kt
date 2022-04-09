package org.sopt.mvvmdiaryexample.data.dao

import androidx.room.*
import org.sopt.mvvmdiaryexample.data.entity.DiaryEntity

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiary(diary: DiaryEntity)

    @Query("SELECT * FROM diaryEntity WHERE id = :diaryId")
    fun getDiary(diaryId: String): DiaryEntity

    @Query("SELECT * FROM diaryEntity")
    fun getAllDiaries(): List<DiaryEntity>

    @Update
    fun updateDiary(diary: DiaryEntity)

    @Delete
    fun deleteDiary(diary: DiaryEntity)

    @Query("DELETE FROM diaryEntity WHERE id = :diaryId")
    fun deleteDiary(diaryId: String)
}