package org.sopt.mvvmdiaryexample.data

import org.sopt.mvvmdiaryexample.data.dao.DiaryDao
import org.sopt.mvvmdiaryexample.data.entity.DiaryEntity
import org.sopt.mvvmdiaryexample.data.executor.DispatchExecutors
import org.sopt.mvvmdiaryexample.domain.Diary

class DiaryLocalSource(
    private val diariesDao: DiaryDao,
    private val dispatchExecutors: DispatchExecutors = DispatchExecutors.getInstance(),
) {
    fun getDiary(diaryId: String, onSuccess: (Diary) -> Unit, onFailure: (Throwable) -> Unit) {
        dispatchExecutors.ioThread.execute {
            try {
                val diaryEntity = diariesDao.getDiary(diaryId)

//                val diary = diaryEntity.takeIf { false }?.let {
////                    diaryEntity.toDiary()
//                }
                dispatchExecutors.mainThread.execute { onSuccess(diaryEntity.toDiary()) }
            } catch (e: Exception) {
                dispatchExecutors.mainThread.execute {
                    onFailure(e)
                }
            }
        }
    }

    fun getDiary(diaryId: String, onResult: (Result<Diary>) -> Unit) {
        dispatchExecutors.ioThread.execute {
            val result = runCatching { diariesDao.getDiary(diaryId) }
                .map { it.toDiary() }

            dispatchExecutors.mainThread.execute {
                onResult(result)
            }
        }

        dispatchExecutors.ioThread.execute {
            runCatching { diariesDao.getDiary(diaryId) }
                .map { it.toDiary() }
                .also {
                    dispatchExecutors.mainThread.execute { onResult(it) }
                }
        }
    }

    fun saveDiary(diary: Diary, onResult: (Result<Unit>) -> Unit) {
        dispatchExecutors.ioThread.execute {
            runCatching { diariesDao.insertDiary(diary.toDiaryEntity()) }
                .also {
                    dispatchExecutors.mainThread.execute { onResult(it) }
                }
        }
    }// Exception , runTimeException

    private fun Diary.toDiaryEntity(): DiaryEntity = DiaryEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        createdDate = this.createDate,
    )

    private fun DiaryEntity.toDiary(): Diary = Diary(
        id = this.id,
        title = this.title,
        content = this.content,
        createDate = this.createdDate,
    )
}