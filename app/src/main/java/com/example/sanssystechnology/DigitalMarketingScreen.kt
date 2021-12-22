package com.example.sanssystechnology

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sanssystechnology.databinding.FragmentDigitalMarketingScreenBinding
import com.example.sanssystechnology.databinding.FragmentWebDevelopmentScreenBinding

class DigitalMarketingScreen : Fragment() {
    private var _binding: FragmentDigitalMarketingScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentDigitalMarketingScreenBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}