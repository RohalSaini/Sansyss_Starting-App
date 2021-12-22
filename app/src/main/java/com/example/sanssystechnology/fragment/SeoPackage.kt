package com.example.sanssystechnology.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sanssystechnology.R
import com.example.sanssystechnology.databinding.FragmentSeoPackageBinding
import com.example.sanssystechnology.databinding.FragmentWebsitePackagesBinding
import com.example.sanssystechnology.modal.Package
import com.example.sanssystechnology.recyclerview.WebsitePackageRecyclerView

class SeoPackage : Fragment() {
    private var _binding: FragmentSeoPackageBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: WebsitePackageRecyclerView
    lateinit var list:ArrayList<Package>
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentSeoPackageBinding.inflate(inflater, container, false)
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
    private fun intiallizeRecyclerView() {
        list = ArrayList()
        list.add( Package(
            name = "Basic CEO Package",
            originalvalue = 49000,
            value =39000
        )
        )
        list.add( Package(
            name = "Silver CEO Package",
            originalvalue = 99990,
            value =89000
        )
        )
        list.add( Package(
            name = "Gold CEO Package",
            originalvalue = 49000,
            value =39000
        )
        )
        list.add( Package(
            name = "Platinum CEO Package",
            originalvalue = 16900,
            value =14999
        )
        )
        val manager= GridLayoutManager(context,1)
        adapter = WebsitePackageRecyclerView(requireContext())
        binding.adapter.layoutManager = manager
        binding.adapter.adapter = adapter
        adapter.setData(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiallizeRecyclerView()
    }
}