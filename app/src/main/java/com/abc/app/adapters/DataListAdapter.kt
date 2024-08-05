package com.abc.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abc.app.databinding.ListItemBinding
import com.abc.app.domain.Project

class DataListAdapter(val isEmpty: (Boolean) -> Unit) : ListAdapter<Project, RecyclerView.ViewHolder>(
    RecordsDiffCallback()
){
    private var data = listOf<Project>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecordViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as RecordViewHolder).bind(data)
    }

    fun publishData(data: List<Project>) {
        this.data = data
        submitList(this.data)
    }

    class RecordViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Project) {
            binding.apply {
                bindImageFromUrl(ivRecord, item.mainImageURL)
                tvTitle.text = item.title
                tvDesc.text = item.shortDescription
            }
        }
    }
}

class RecordsDiffCallback : DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}

