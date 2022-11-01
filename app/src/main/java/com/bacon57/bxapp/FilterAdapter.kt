package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterCategoryBinding
import com.bacon57.bxapp.model.Category

class FilterAdapter(
    private val onClickListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    var categories = mutableListOf<Category>()

    fun setCategoriesList(categories: List<Category>) {
        this.categories = categories.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
//        holder.binding
        holder.render(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

class CategoryViewHolder(
    val binding: AdapterCategoryBinding,
    val onClickListener: (Category) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun render(category: Category) {
        binding.tvFilterName.text = category.name.uppercase()
        binding.root.rootView.setOnClickListener { onClickListener(category) }
    }

}
