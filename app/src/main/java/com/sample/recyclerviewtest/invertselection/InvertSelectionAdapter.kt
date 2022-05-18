package com.sample.recyclerviewtest.invertselection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerviewtest.Fruit
import com.sample.recyclerviewtest.R
import com.sample.recyclerviewtest.databinding.FruitItemBinding
import com.sample.recyclerviewtest.multiplechoice.MultipleChoiceAdapter

/**
 *@project: com.sample.recyclerviewtest.invertselection
 *@date: 2022/5/18 19:43
 *@author: sully-iwnl-
 *@description:
 */
class InvertSelectionAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<InvertSelectionAdapter.ViewHolder>() {

    companion object {
        const val TAG = "InvertSelectionAdapter"
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

    /**
     * 反选方法
     *
     * @return List<Fruit> 返回返选后的集合
     */
    fun setInvertSelection(): List<Fruit> {
        val newList = ArrayList<Fruit>()
        fruitList.forEach {
            if (!it.isChecked) {
                newList.add(it)
            }
        }
        fruitList.forEach {
            it.isChecked = !it.isChecked
        }
        notifyDataSetChanged()
        return newList
    }

    /**
     * 清除所有所选
     */
    fun setClear() {
        fruitList.forEach {
            if (it.isChecked) {
                it.isChecked = false
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = fruitList.size

    fun setOnMultipleChoiceClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, list: List<Fruit>)
    }
}