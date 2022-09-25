package com.bacon57.bxapp

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterProductBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class ProductViewHolder(
    val binding: AdapterProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    val format: NumberFormat = NumberFormat.getNumberInstance()

    fun render(product: Product) {
        binding.tvName.text = product.name
        binding.tvCategory.text = "Categoria"
        binding.tvPrice.text = format.format(product.price)
//        binding.ivImage
        Picasso.get().load("https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg")
            .into(binding.ivImage)
        binding.btAdd.setOnClickListener{
            Toast.makeText(binding.root.context, product.name, Toast.LENGTH_LONG).show()
        }
    }

}
