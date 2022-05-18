package com.sample.recyclerviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sample.recyclerviewtest.databinding.ActivityMainBinding
import com.sample.recyclerviewtest.invertselection.InvertSelectionActivity
import com.sample.recyclerviewtest.multiplechoice.MultipleChoiceActivity
import com.sample.recyclerviewtest.radio.RadioActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //单选
        binding.radioButton.setOnClickListener {
            RadioActivity.start(this)
        }
        //多选
        binding.multipleChoiceButton.setOnClickListener {
            MultipleChoiceActivity.start(this)
        }
        //多选后反选
        binding.invertSelectionButton.setOnClickListener {
            InvertSelectionActivity.start(this)
        }
    }

}