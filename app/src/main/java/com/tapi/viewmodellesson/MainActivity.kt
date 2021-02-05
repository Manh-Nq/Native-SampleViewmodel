package com.tapi.viewmodellesson

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.tapi.viewmodellesson.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var frg: MainFrg




           private lateinit var viewModel: MyViewModel
    private val viewModelNew: MyViewModel by viewModels {
        MyViewmodelFactory(
            1,
            "12",
            "12",
            application
        )
    }

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frg = MainFrg()

//        viewModel = ViewModelProvider(viewModelStore, MyViewmodelFactory(1, "12", "12", application)).get(MyViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.ln_main, frg).commit()

    }
}