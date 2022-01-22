package com.ernazar.newsapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ernazar.newsapp.presentation.fragment.EverythingFragment
import com.ernazar.newsapp.presentation.fragment.TopHeadlinesFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class VPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle), KoinComponent{

    private val topHeadlinesFragment: TopHeadlinesFragment by inject()
    private val everythingFragment: EverythingFragment by inject()

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> topHeadlinesFragment
            else -> everythingFragment
        }

    }

}