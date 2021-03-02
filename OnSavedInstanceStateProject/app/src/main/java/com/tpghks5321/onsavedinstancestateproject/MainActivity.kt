package com.tpghks5321.onsavedinstancestateproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tpghks5321.onsavedinstancestateproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    private val stateViewModel: StateHandleViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        stateViewModel.getName().observe(this, Observer {
            binding.liveTextView.text = it
        })

        binding.liveTextView.setOnClickListener {
            stateViewModel.saveNewName(Calendar.getInstance().time.toString())
        }

    }


    // 화면 전환시 현재 검색어 상태 저장
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(SEARCH_KEY, binding.editText.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    //화면 전환후 저장된 검색어 가지고 오기
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val searchText = savedInstanceState.getString(SEARCH_KEY)
        searchText?.let { text ->
            binding.editText.setText(text)
        }
    }

    companion object {
        const val SEARCH_KEY = "SEARCH_KEY"
    }
}