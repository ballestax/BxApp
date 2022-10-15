package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterIngredientBinding

class IngredientAdapter: RecyclerView.Adapter<IngredientViewHolder>(){

    var ingredients = mutableListOf<Ingredient>()

    fun setIngredient(ingredients: List<Ingredient>){
        this.ingredients = ingredients.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterIngredientBinding.inflate(inflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
//        holder.binding
        holder.render(ingredient)
    }

    override fun getItemCount(): Int {
        return  ingredients.size
    }
}

class IngredientViewHolder(val binding: AdapterIngredientBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(ingredient: Ingredient) {
        binding.cbSelected.text = ingredient.name.uppercase()
        // binding.btAdd.setOnClickListener{ onClickListener(product) }
    }

}
