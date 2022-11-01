package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterAdditionalBinding
import com.bacon57.bxapp.model.Additional

class AdditionalAdapter: RecyclerView.Adapter<AdditionalViewHolder>(){

    var addtionals = mutableListOf<Additional>()

    fun setAdditional(addtionals: List<Additional>){
        this.addtionals = addtionals.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterAdditionalBinding.inflate(inflater, parent, false)
        return AdditionalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdditionalViewHolder, position: Int) {
        val ingredient = addtionals[position]
//        holder.binding
        holder.render(ingredient)
    }

    override fun getItemCount(): Int {
        return  addtionals.size
    }
}

class AdditionalViewHolder(val binding: AdapterAdditionalBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(additional: Additional) {
        binding.tvName.text = additional.name.uppercase()
        binding.tvPrice.text =additional.price.toString()
        // binding.btAdd.setOnClickListener{ onClickListener(product) }
    }

}
