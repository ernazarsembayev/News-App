package com.ernazar.newsapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ernazar.newsapp.databinding.FragmentEverythingBinding
import com.ernazar.newsapp.domain.usecases.GetEverythingUseCase
import org.koin.android.ext.android.inject

class EverythingFragment : Fragment() {

    private val getEverythingUseCase: GetEverythingUseCase by inject()

    private lateinit var binding: FragmentEverythingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEverythingBinding.inflate(layoutInflater)



        return binding.root

    }

}