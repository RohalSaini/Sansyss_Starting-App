package com.example.sanssystechnology.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sanssystechnology.R
import com.example.sanssystechnology.modal.Item

class HomeRecyclerView (var context: Context):RecyclerView.Adapter<HomeRecyclerView.ViewHolder>(){
    var oldList:ArrayList<Item> = ArrayList()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.course_name)
        var description: TextView = view.findViewById(R.id.course_description)
        var img:ImageView = view.findViewById(R.id.course_pic)
        var btnMore: Button = view.findViewById(R.id.btn_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_screen,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set value here
        holder.name.text = oldList[position].name
        holder.description.text = oldList[position].description
        holder.btnMore.setOnClickListener {
            println("More iTem is clicked")
        }
    }

    override fun getItemCount(): Int {
        return oldList.size
    }
    class MyDiffUtil(var oldList: MutableList<Item>,var newList: MutableList<Item>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.count()
        }

        override fun getNewListSize(): Int {
            return newList.count()
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition:Int):Boolean {
            return when {

                else -> true
            }
        }
    }
    fun setData(newPersonList:ArrayList<Item>) {
        val diffUtil = MyDiffUtil(oldList, newPersonList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldList.clear()
        oldList.addAll(newPersonList)
        diffResults.dispatchUpdatesTo(this)
    }
}