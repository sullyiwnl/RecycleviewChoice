package com.sample.recyclerviewtest.multiplechoice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.recyclerviewtest.Fruit
import com.sample.recyclerviewtest.R
import com.sample.recyclerviewtest.databinding.ActivityMultipleChoiceBinding

class MultipleChoiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultipleChoiceBinding
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruit()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MultipleChoiceAdapter(fruitList)
        binding.recyclerView.adapter = adapter
        adapter.setOnMultipleChoiceClickListener(object : MultipleChoiceAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, list: List<Fruit>) {
                Log.d(TAG, "checked list.size: ${list.size}")
                list.forEach {
                    Log.d(TAG, " checked fruit name: ${it.name}")
                }
            }
        })
    }

    private fun initFruit() {
        fruitList.add(Fruit("Apple", R.drawable.apple_pic, false))
        fruitList.add(Fruit("Banana", R.drawable.banana_pic,false))
        fruitList.add(Fruit("Orange", R.drawable.orange_pic,false))
        fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic,false))
        fruitList.add(Fruit("Pear", R.drawable.pear_pic, false))
        fruitList.add(Fruit("Grape", R.drawable.grape_pic, false))
        fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic, false))
        fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic, false))
        fruitList.add(Fruit("Cherry", R.drawable.cherry_pic, false))
        fruitList.add(Fruit("Mango", R.drawable.mango_pic, false))
    }

    companion object {
        const val TAG = "MultipleChoiceActivity"

        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MultipleChoiceActivity::class.java)
            context.startActivity(starter)
        }
    }

}