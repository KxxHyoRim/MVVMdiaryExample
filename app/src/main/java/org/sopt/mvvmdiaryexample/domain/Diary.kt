package org.sopt.mvvmdiaryexample.domain

import java.util.*

data class Diary(
    val id: Long,
    val title: String,
    val content: String,
    val createDate: Date,
)