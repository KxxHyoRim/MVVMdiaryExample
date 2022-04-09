package org.sopt.mvvmdiaryexample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.sopt.mvvmdiaryexample.data.converter.DateTypeConverter
import org.sopt.mvvmdiaryexample.data.dao.DiaryDao
import org.sopt.mvvmdiaryexample.data.entity.DiaryEntity

@TypeConverters(DateTypeConverter::class)
@Database(entities = [DiaryEntity::class], version = 1)
/** version 은 나중에 DB를 사용하다가 schema가 바뀔 경우에 수정해줌
 * Schema Migration을 해야하는데 이때 사용함*/
abstract class DailyDiaryDatabase : RoomDatabase() {

    abstract fun getDiariesDao(): DiaryDao

    companion object {
        private var instance: DailyDiaryDatabase? = null
        fun getInstance(context: Context): DailyDiaryDatabase {
            return instance ?: synchronized<DailyDiaryDatabase>(this) {
                newInstance(context).also { instance = it }
            }
        }

        @JvmStatic
        fun newInstance(context: Context): DailyDiaryDatabase {
            return Room.databaseBuilder(
                context,
                DailyDiaryDatabase::class.java,
                "DailyDiaryDatabase"
            ).build()
            // .allowMainThreadQueries().build() // bad code..
        }
    }
}