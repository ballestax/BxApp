package com.bacon57.bxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bacon57.bxapp.databinding.FragmentProductListBinding


class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: ProductListViewModel

    private val retrofitService = RetrofitService.getInstance()

    lateinit var adapterProd: ItemAdapter
    val adapterCat = FilterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(
            this, ProductViewModelFactory(
                ProductsRepository(retrofitService),
                CategoriesRepository(retrofitService),
                -1
            )
        )[ProductListViewModel::class.java]

        adapterProd = ItemAdapter({ product -> onItemSelected(product) },
            { product -> onItemCustomSelected(product) })

        binding.categoriesList.adapter = adapterCat
        binding.categoriesList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvProductsList.adapter = adapterProd
        binding.rvProductsList.layoutManager = LinearLayoutManager(this.context)
//        binding.rvProductsList.layoutManager = GridLayoutManager(this.context,2)

        viewModel.productList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            adapterProd.setproductList(it)
        })

        viewModel.errorMessage.observe(getViewLifecycleOwner(), Observer {

        })

        viewModel.categoriesList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            adapterCat.setCategoriesList(it)
        })

        viewModel.progressVisible.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
//            binding.progressBar.set
        })

        viewModel.getAllCategories()
        viewModel.getAllProducts()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemSelected(product: Product) {

        Toast.makeText(this.context, "${product.name} [${product.price}]", Toast.LENGTH_SHORT)
            .show()
    }

    fun onItemCustomSelected(product: Product) {

        Toast.makeText(
            this.context,
            "${product.name.uppercase()} [${product.price}]",
            Toast.LENGTH_SHORT
        ).show()

        val idProduct = product.id
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(idProduct)
        findNavController().navigate(action)

    }

}