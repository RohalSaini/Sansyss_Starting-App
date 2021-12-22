package com.example.sanssystechnology.fragment.websitepackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sanssystechnology.R
import com.example.sanssystechnology.databinding.FragmentAboutScreenBinding
import com.example.sanssystechnology.databinding.FragmentWebsitePackageScreenBinding
import com.example.sanssystechnology.databinding.FragmentWebsitePackagesBinding
import com.example.sanssystechnology.modal.Package
import com.example.sanssystechnology.recyclerview.HomeRecyclerView
import com.example.sanssystechnology.recyclerview.WebsitePackageRecyclerView

class WebsitePackages : Fragment() {
    private var _binding: FragmentWebsitePackagesBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: WebsitePackageRecyclerView
    lateinit var list:ArrayList<Package>
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentWebsitePackagesBinding.inflate(inflater, container, false)
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
            name = "Dynamic Website",
            originalvalue = 49000,
            value =39000
        ))
        list.add( Package(
            name = "CMS Website",
            originalvalue = 99990,
            value =89000
        ))
        list.add( Package(
            name = "Dynamic Website",
            originalvalue = 49000,
            value =39000
        ))
        list.add( Package(
            name = "E-commercial Website",
            originalvalue = 16900,
            value =14999
        ))
        list.add( Package(
            name = "Corporate Website",
            originalvalue = 19900,
            value =18500
        ))
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