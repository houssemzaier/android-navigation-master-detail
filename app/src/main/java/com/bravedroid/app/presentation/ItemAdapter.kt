package com.bravedroid.app.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.app.databinding.ItemBinding

class ItemAdapter : ListAdapter<ItemUiModel, ItemAdapter.ItemViewHolder>(DiffUtilCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ItemViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val binding = ItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ItemViewHolder(binding)
            }
        }

        fun bind(itemUiModel: ItemUiModel) {
            binding.itemTextView.text = itemUiModel.name
            binding.itemTextView.setCompoundDrawablesWithIntrinsicBounds(
                itemUiModel.image, 0, 0, 0,
            )
        }
    }

    private object DiffUtilCallBack : DiffUtil.ItemCallback<ItemUiModel>() {
        override fun areItemsTheSame(oldItem: ItemUiModel, newItem: ItemUiModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ItemUiModel, newItem: ItemUiModel): Boolean =
            oldItem.name == newItem.name
    }
}

