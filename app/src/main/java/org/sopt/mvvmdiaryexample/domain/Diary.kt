package org.sopt.mvvmdiaryexample.domain

import java.util.*

data class Diary(
    val id: String,
    val title: String,
    val content: String,
    val createDate: Date,
)