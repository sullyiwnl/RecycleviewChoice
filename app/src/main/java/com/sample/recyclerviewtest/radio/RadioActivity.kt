package com.sample.recyclerviewtest.radio

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.recyclerviewtest.Fruit
import com.sample.recyclerviewtest.R
import com.sample.recyclerviewtest.databinding.ActivityRadioBinding


class RadioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadioBinding

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFruit()
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = RadioAdapter(fruitList)
        binding.recyclerView.adapter = adapter
        adapter.setItemPosition(4)
        adapter.setOnItemClickListener(object: RadioAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@RadioActivity, "you clicked ${fruitList[position].name}", Toast.LENGTH_SHORT).show()
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
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, RadioActivity::class.java)
            context.startActivity(starter)
        }
    }
}