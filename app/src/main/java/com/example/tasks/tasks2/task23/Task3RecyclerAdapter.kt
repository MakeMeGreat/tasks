package com.example.tasks.tasks2.task23

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tasks.databinding.Task3RecyclerItemBinding

class Task3RecyclerAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<Int, Task3RecyclerAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(val binding: Task3RecyclerItemBinding) : ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.apply {
                textView.text = "Item ${item}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            Task3RecyclerItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
        holder.bind(item)
    }

    companion object DiffCallback: ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
}