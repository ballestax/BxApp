package com.bacon57.bxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bacon57.bxapp.databinding.FragmentProductDetailBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var adapterIngredient: IngredientAdapter
    lateinit var adapterAdditional: AdditionalAdapter
    lateinit var adapterPresentation: PresentationAdapter

    val format: NumberFormat = NumberFormat.getNumberInstance()

    lateinit var viewModel: ProductDetailViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        adapterPresentation = PresentationAdapter()

        adapterIngredient = IngredientAdapter()

        adapterAdditional = AdditionalAdapter()

        val idProduct:Long  = arguments?.getLong("idProduct") ?: 0
        Log.d("ProductDetailFragment", "id::$idProduct")

        viewModel = ViewModelProvider(
            this, ProductViewModelFactory(
                ProductsRepository(retrofitService),
                CategoriesRepository(retrofitService),
                idProduct
            )
        )[ProductDetailViewModel::class.java]

        Log.d("ProductDetailFragment", "${viewModel.product}")

        viewModel.product.observe(viewLifecycleOwner) {
            Log.d("ProductDetailFragment", "onCreate: $it -> $idProduct")
            renderProduct(it)
        }

        viewModel.getProductById(idProduct)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderProduct(product: Product){
        binding.tvName.text = product.name.uppercase()
        binding.tvPrice.text = format.format(product.price)
        binding.tvQuantity.text = "1"
        binding.tvCategory.text = product.category

        Picasso.get().load("https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg")
            .into(binding.ivImage)

        binding.tvTitlePresentations.text = "Presentaciones"

        adapterPresentation.setPresentation(product.presentations)
        binding.rvPresentations.adapter = adapterPresentation
        binding.rvPresentations.layoutManager =
            GridLayoutManager(this.context, 2)

        binding.tvTitleIngredients.text = "Ingredientes"

        adapterIngredient.setIngredient(product.ingredients)
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.tvTitleAditionals.text = "Adicionales"

        adapterAdditional.setAdditional(product.additionals)
        binding.rvAditionals.adapter = adapterAdditional
        binding.rvAditionals.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)



    }


}