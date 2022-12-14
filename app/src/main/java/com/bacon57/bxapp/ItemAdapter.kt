package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterProductBinding
import com.bacon57.bxapp.model.Product

class ItemAdapter(
    private val onClickListener: (Product) ->Unit,
    private val onClickListener2: (Product) ->Unit,
) : RecyclerView.Adapter<ProductViewHolder>() {

    var products = mutableListOf<Product>()

    fun setproductList(products: List<Product>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding, onClickListener, onClickListener2)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
//        holder.binding
        holder.render(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

interface OnClickListener {
    fun onItemClick(product: Product)
    fun onItemCustomClick(product: Product)
}