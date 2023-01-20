package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterIngredientBinding
import com.bacon57.bxapp.model.Ingredient

class IngredientAdapter : RecyclerView.Adapter<IngredientViewHolder>() {

    var ingredients = mutableListOf<Ingredient>()
    private val checked = mutableListOf<Boolean>()

    fun setIngredient(ingredients: List<Ingredient>) {
        this.ingredients = ingredients.toMutableList()
        checked.clear()
        ingredients.forEach { checked.add(false) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterIngredientBinding.inflate(inflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.render(ingredient, checked, position)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}

class IngredientViewHolder(val binding: AdapterIngredientBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun render(ingredient: Ingredient, checked: MutableList<Boolean>, position: Int) {
        var isChecked = checked[position]
        binding.cbSelected.text = ingredient.name.uppercase()
        binding.cbSelected.isChecked = isChecked
        binding.cbSelected.setOnClickListener{
            checked[position] = !isChecked
        }
    }

}
