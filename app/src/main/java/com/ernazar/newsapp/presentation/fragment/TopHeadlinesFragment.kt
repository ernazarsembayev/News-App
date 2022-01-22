package com.ernazar.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ernazar.newsapp.databinding.FragmentTopHeadlinesBinding

class TopHeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentTopHeadlinesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopHeadlinesBinding.inflate(layoutInflater)



        return binding.root
    }


}