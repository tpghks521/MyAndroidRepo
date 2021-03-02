package com.tpghks5321.onsavedinstancestateproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

	lateinit var editText: EditText

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		editText = findViewById(R.id.edit_text)

	}

	// 화면 전환시 현재 검색어 상태 저장
	override fun onSaveInstanceState(outState: Bundle) {
		outState.run {
			putString(SEARCH_KEY, editText.text.toString())
		}

		super.onSaveInstanceState(outState)
	}

	//화면 전환후 저장된 검색어 가지고 오기
	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		val searchText = savedInstanceState.getString(SEARCH_KEY)
		searchText?.let { text ->
			editText.setText(text)
		}
	}

	companion object {
		const val SEARCH_KEY = "SEARCH_KEY"
	}
}