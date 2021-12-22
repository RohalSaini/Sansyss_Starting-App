package com.example.sanssystechnology.fragment.dashbaord.viewmodal

import androidx.lifecycle.ViewModel
import com.example.sanssystechnology.modal.Item

class DashBoardViewModal: ViewModel() {
    var courseList = ArrayList<Item>()
    init {
        courseList = ArrayList<Item>()
        addValues()
    }

    fun addValues() {
        var course: Item = Item(
            name = "Web Developement",
            description = "Give your website and amazing UI with our customise Web Design and Development packages in our Company in Hoshiarpur Punjab. If you are going to be online, then you need a proper website- one built by professionals who are passionate about what they do, and offer you comprehensive web design packages prices in India.",
            imgPath = ""
        )
        courseList.add(course)
        var course2: Item = Item(
            name = "Search Engine Optimiztion",
            description = "Best & Affordable SEO Packages in India can get you higher keywords rankings & traffic. Our Company’s in Hoshiarpur Punjab SEO plans help to maximize your results in Google. Looking for SEO services, but worried about the budget? Relax! SEO Experts India offers affordable SEO packages in our Company in Hoshiarpur Punjab.",
            imgPath = " "
        )
        courseList.add(course2)
    }
}