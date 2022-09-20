package com.bacon57.bxapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.ProductFilterBinding

class FilterAdapter(private val context: Context) : BaseAdapter(){

    var categories = mutableListOf<Category>()

    fun setCategoryList(categories: List<Category>){
        this.categories = categories.toMutableList()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val inflater = LayoutInflater.from(parent?.context ?: context)
        val binding = ProductFilterBinding.inflate(inflater, parent, false)
        binding.tvFilterName.text = categories.get(position).name
        return binding.root
    }
}