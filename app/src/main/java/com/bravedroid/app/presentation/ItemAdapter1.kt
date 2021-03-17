package com.bravedroid.app.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.app.databinding.ItemBinding

class ItemAdapter1 : RecyclerView.Adapter<ItemAdapter1.ItemViewHolder>() {
    private val itemUiModelList = mutableListOf<ItemUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int){
        val itemUiModel = itemUiModelList[position]
        holder.bind(itemUiModel)
    }

    override fun getItemCount(): Int = itemUiModelList.count()

    fun add(itemUiModelList: List<ItemUiModel>) {
        this.itemUiModelList.clear()
        this.itemUiModelList.addAll(itemUiModelList)
        notifyDataSetChanged()
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
}

