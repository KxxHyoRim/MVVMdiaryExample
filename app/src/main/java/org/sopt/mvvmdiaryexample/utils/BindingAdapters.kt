package org.sopt.mvvmdiaryexample.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * @BindingAdapter("createdDate"),  @BindingAdapter("app:createdDate")
 * 둘 다 사용 가능하지만, app: 을 붙이는 것이 정석적인 방법이라고 한다
 * */
//@BindingAdapter("createdDate")
@BindingAdapter("app:createdDate")
fun bindMemoCreatedDate(textView: TextView, date: Date?) {  //null 이 들어올 수도 있음
    if (date == null) return
    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    textView.text = simpleDateFormat.format(date)
}