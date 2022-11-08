package com.bacon57.bxapp

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterProductBinding
import com.bacon57.bxapp.model.Product
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class ProductViewHolder(
    val binding: AdapterProductBinding,
    val onClickListener: (Product)->Unit,
    val onClickListener2: (Product)->Unit,
) : RecyclerView.ViewHolder(binding.root) {

    val format: NumberFormat = NumberFormat.getNumberInstance()

    fun render(product: Product) {
        binding.tvName.text = product.name
        binding.tvCategory.text = product.category.replaceFirstChar { char-> char.uppercase() }
        binding.tvPrice.text = format.format(product.price)
//        binding.ivImage
        var url = "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
        if(product.image!=null){
            url = "https://"+product.image
            Log.d("AppDebug", url)
        }

        Picasso.get().load(url)
            .into(binding.ivImage)
        binding.btAdd.setOnClickListener{
            onClickListener(product)
        }
        binding.btPref.setOnClickListener{
            onClickListener2(product)
        }
    }

}
