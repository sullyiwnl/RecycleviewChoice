package com.sample.recyclerviewtest.multiplechoice

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerviewtest.Fruit
import com.sample.recyclerviewtest.R
import com.sample.recyclerviewtest.databinding.FruitItemBinding

/**
 *@project: com.sample.recyclerviewtest.multiplechoice
 *@date: 2022/5/18 17:59
 *@author: sully-iwnl-
 *@description:
 */
class MultipleChoiceAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<MultipleChoiceAdapter.ViewHolder>() {

    companion object {
        const val TAG = "MultipleChoiceAdapter"
    }
    private var mOnItemClickListener: OnItemClickListener? = null
    private val checkList = ArrayList<Fruit>()
    inner class ViewHolder(val binding: FruitItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FruitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]

        if (fruit.isChecked) {
            holder.binding.check.setImageResource(R.drawable.checked)
        } else {
            holder.binding.check.setImageResource(R.drawable.unchecked)
        }
        holder.binding.fruitImage.setImageResource(fruit.imageId)
        holder.binding.fruitName.text = fruit.name
        holder.binding.root.setOnClickListener {
                if (fruitList[position].isChecked) {
                    fruit.isChecked = false
                    notifyItemChanged(position)
                } else {
                    fruit.isChecked = true
                    notifyItemChanged(position)
                }
            checkList.clear()
            fruitList.forEach {
                if (it.isChecked) {
                    checkList.add(it)
                }
            }
        }
        mOnItemClickListener?.onItemClick(position, checkList)
    }

    override fun getItemCount() = fruitList.size

    fun setOnMultipleChoiceClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, list: List<Fruit>)
    }
}