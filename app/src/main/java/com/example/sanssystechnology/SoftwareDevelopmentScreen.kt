package com.example.sanssystechnology

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanssystechnology.databinding.FragmentSoftwareDevelopmentScreenBinding
import com.example.sanssystechnology.databinding.FragmentWebDevelopmentScreenBinding

class SoftwareDevelopmentScreen : Fragment() {
    private var _binding: FragmentSoftwareDevelopmentScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentSoftwareDevelopmentScreenBinding.inflate(inflater, container, false)
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