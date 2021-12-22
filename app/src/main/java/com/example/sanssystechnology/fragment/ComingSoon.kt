package com.example.sanssystechnology.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanssystechnology.R
import com.example.sanssystechnology.databinding.FragmentAboutScreenBinding
import com.example.sanssystechnology.databinding.FragmentComingSoonBinding

class ComingSoon : Fragment() {
    private var _binding: FragmentComingSoonBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentComingSoonBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }
}