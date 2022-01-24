package com.ernazar.newsapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Добавление пенели действии
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.bookmark) {
//            val bookmarkActivityIntent = Intent(this, BookmarkActivity::class.java)
//            startActivity(bookmarkActivityIntent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}