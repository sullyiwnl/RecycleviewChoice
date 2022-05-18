package com.sample.recyclerviewtest.radio


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.recyclerviewtest.Fruit
import com.sample.recyclerviewtest.R
import com.sample.recyclerviewtest.databinding.FruitItemBinding

/**
 *@project: com.sample.recyclerviewtest
 *@date: 2022/5/15 14:00
 *@author: sully-iwnl-
 *@description:
 */
class RadioAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<RadioAdapter.ViewHolder>() {

    companion object {
        const val TAG = "FruitAdapter"
    }

    private var checkPos = -1
    private var mOnItemClickListener: OnItemClickListener? = null

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
            mOnItemClickListener?.onItemClick(position)
            if (checkPos != position) {
                //取消上一个位置的勾选状态
                fruitList[checkPos].isChecked = false
                notifyItemChanged(checkPos)
                //设置新的勾选状态
                checkPos = holder.bindingAdapterPosition
                fruitList[checkPos].isChecked = true
                notifyItemChanged(checkPos)
            }
        }
    }

    override fun getItemCount() = fruitList.size

    /**
     *
     * @param position Int 默认选中位置
     */
    fun setItemPosition(position: Int) {
        checkPos = position
        fruitList[checkPos].isChecked = true
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mOnItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}