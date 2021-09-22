package com.clonecoding.todaysaying

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

/**
 * Remote config
 * ViewPager2
 */
class MainActivity : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    /**
     * 뷰를 초기화 합니다.
     */
    private fun initViews() {

        this.viewPager.adapter = QuotesPagerAdapter(listOf(
            Quote("테스트 명언", "테스트 명언 대상")
        ))
    }
}