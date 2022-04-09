package org.sopt.mvvmdiaryexample.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class DiaryEntity(
    val title: String,
    val content: String,
    val createdDate: Date,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
)
