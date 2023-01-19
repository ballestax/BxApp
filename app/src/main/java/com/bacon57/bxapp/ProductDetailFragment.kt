package com.bacon57.bxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bacon57.bxapp.databinding.FragmentProductDetailBinding
import com.bacon57.bxapp.model.Product
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.text.DecimalFormat
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

        val idProduct: Long = arguments?.getLong("idProduct") ?: 0
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

    private fun renderProduct(product: Product) {
        binding.tvName.text = product.name.uppercase()
        binding.tvPrice.text = format.format(product.price)
        binding.tvQuantity.text = "1"
        binding.tvCategory.text = product.category

        var url = "https://icon-library.com/images/no-image-icon/no-image-icon-0.jpg"
        if(product.image!=null){
            url = product.image
        }
        Picasso.get().load(url)
            .into(binding.ivImage)

        val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(requireContext().getDrawable(R.drawable.divider)!!)

        binding.tvTitlePresentations.text = "Presentaciones"

        adapterPresentation.setPresentation(product.presentations)
        binding.rvPresentations.adapter = adapterPresentation
        binding.rvPresentations.layoutManager =
            GridLayoutManager(this.context, 2)

        binding.tvTitleIngredients.text = "Ingredientes"

        adapterIngredient.setIngredient(product.ingredients)
        binding.rvIngredients.adapter = adapterIngredient
        binding.rvIngredients.addItemDecoration(itemDecoration)
        binding.rvIngredients.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.tvTitleAdditionals.text = "Adicionales"

        adapterAdditional.setAdditional(product.additionals)
        binding.rvAditionals.adapter = adapterAdditional
        binding.rvAditionals.addItemDecoration(itemDecoration)
        binding.rvAditionals.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.btPlus.setOnClickListener(View.OnClickListener {
            var num: Int = Integer.valueOf(binding.tvQuantity.text.toString())
            val LIM = 100
            if (num < LIM - 1)
                binding.tvQuantity.text = String.format("%d", ++num)
            calculatePrice(num)
        })

        binding.btMinus.setOnClickListener(View.OnClickListener {
            var num: Int = Integer.valueOf(binding.tvQuantity.text.toString())
            val LIM = 1
            if (num > LIM)
                binding.tvQuantity.text = String.format("%d", --num)
            calculatePrice(num)
        })

        binding.btCancel.setOnClickListener(View.OnClickListener { onCancelAction() })
        binding.btConfirm.setOnClickListener(View.OnClickListener { onConfirmAction() })
    }

    private fun calculatePrice(quantity: Int) {
        val df: DecimalFormat = format as DecimalFormat
        df.isParseBigDecimal = true
        var price = df.parse(binding.tvPrice.text.toString()) as BigDecimal
        val priceBase = price
        if (quantity >= 1) {
            price = price.multiply(BigDecimal(quantity))
            binding.tvResumePrice.text = "$priceBase x $quantity = $price"
        } else {
            binding.tvResumePrice.text = "$priceBase"
        }
    }

    fun onCancelAction() {
        val action =
            ProductDetailFragmentDirections.actionProductDetailFragmentToProductListFragment()
        findNavController().navigate(action)
    }

    fun onConfirmAction() {
        val action =
            ProductDetailFragmentDirections.actionProductDetailFragmentToOrderFragment()
        findNavController().navigate(action)
    }

    fun parseProduct(){

    }
}