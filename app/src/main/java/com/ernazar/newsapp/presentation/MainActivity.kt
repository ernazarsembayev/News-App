package com.ernazar.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ernazar.newsapp.R
import com.ernazar.newsapp.databinding.ActivityMainBinding
import com.ernazar.newsapp.presentation.adapter.VPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = VPAdapter(supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.top_headlines)
                1 -> tab.text = getString(R.string.everything)
            }
        }.attach()
    }
}