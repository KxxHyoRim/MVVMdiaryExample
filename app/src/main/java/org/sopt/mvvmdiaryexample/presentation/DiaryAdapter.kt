package org.sopt.mvvmdiaryexample.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.mvvmdiaryexample.databinding.ItemDiaryBinding
import org.sopt.mvvmdiaryexample.domain.Diary

class DiaryAdapter(
    private val onDiaryClick: ((Diary) -> Unit)? = null
) : ListAdapter<Diary, DiaryAdapter.ViewHolder>(DiaryComparator()) {

    /** RecyclerView + ListAdapter 사용을 위해서는
     * 1. ViewHolder
     * 2. DiffUtil
     * 3. ListAdapter
     * */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDiaryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = getItem(position)
        holder.bind(diary, onDiaryClick)
    }

    // getItemCount() 메소드의 경우 리사이클러뷰 어댑터는 필수적으로 구현해줘야 하지만
    // 리스트 어댑터는 자체적으로 구현되어 있기에, 직접 구현하지 앟아도 된다.

    class ViewHolder(
        private val binding: ItemDiaryBinding,  // 생성자
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(diary: Diary, onMemoClick: ((Diary) -> Unit)? = null) {
            binding.diary = diary
            binding.root.setOnClickListener { onMemoClick?.invoke(diary) }
        }
    }

    private class DiaryComparator : DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Diary, newItem: Diary) = oldItem == newItem
    }

}