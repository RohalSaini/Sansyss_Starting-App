package com.example.sanssystechnology.recyclerview

import android.content.Context
import android.graphics.Paint
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
import com.example.sanssystechnology.modal.Package

class WebsitePackageRecyclerView (var context: Context):RecyclerView.Adapter<WebsitePackageRecyclerView.ViewHolder>(){
    var oldList:ArrayList<Package> = ArrayList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.course_name)
        var cost: TextView = view.findViewById(R.id.cost)
        var img:ImageView = view.findViewById(R.id.course_pic)
        var cart: Button = view.findViewById(R.id.cart)
        var value: TextView = view.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_website_package_screen,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set value here
        holder.name.text = oldList[position].name
        holder.cost.text = "Rs ${oldList[position].originalvalue}"
        holder.cost.paintFlags = holder.cost.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        holder.value.text = "Rs ${oldList[position].value}"

        holder.cart.setOnClickListener {
            println("Items added to cart")
        }
    }

    override fun getItemCount(): Int {
        return oldList.size
    }
    class MyDiffUtil(var oldList: MutableList<Package>,var newList: MutableList<Package>) : DiffUtil.Callback() {
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
    fun setData(newPersonList:ArrayList<Package>) {
        val diffUtil = MyDiffUtil(oldList, newPersonList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldList.clear()
        oldList.addAll(newPersonList)
        diffResults.dispatchUpdatesTo(this)
    }
}