package com.bacon57.bxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bacon57.bxapp.databinding.FragmentProductListBinding
import com.bacon57.bxapp.model.Category
import com.bacon57.bxapp.model.Product


class ProductListFragment : Fragment() {

    private lateinit var productList: List<Product>
    private lateinit var categoryList: List<Category>
    private var _binding: FragmentProductListBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: ProductListViewModel

    private val retrofitService = RetrofitService.getInstance()

    lateinit var adapterProd: ItemAdapter
    lateinit var adapterCat : FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        productList = listOf<Product>()

        viewModel = ViewModelProvider(
            this, ProductViewModelFactory(
                ProductsRepository(retrofitService),
                CategoriesRepository(retrofitService),
                -1
            )
        )[ProductListViewModel::class.java]

        adapterProd = ItemAdapter({ product -> onItemSelected(product) },
            { product -> onItemCustomSelected(product) })

        adapterCat = FilterAdapter({ category -> onCategorySelected(category) })

        binding.categoriesList.adapter = adapterCat
        binding.categoriesList.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        binding.rvProductsList.adapter = adapterProd
        binding.rvProductsList.layoutManager = LinearLayoutManager(this.context)
//        binding.rvProductsList.layoutManager = GridLayoutManager(this.context,2)

        viewModel.productList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            productList = it
            adapterProd.setproductList(it)
            binding.shimmerFrameLayout.stopShimmer()
            binding.shimmerFrameLayout.visibility = View.GONE
            binding.rvProductsList.visibility = View.VISIBLE
//            if(!categoryList.isEmpty()) {
//                adapterProd.setproductList(filterListByCategory(categoryList[0].name))
//            }
        })

        viewModel.errorMessage.observe(getViewLifecycleOwner(), Observer {

        })

        viewModel.categoriesList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            categoryList= it
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

        val idProduct = product.id
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(idProduct)
        findNavController().navigate(action)

    }

    fun onCategorySelected(category: Category){
        adapterProd.setproductList(filterListByCategory(category.name))
    }

    private fun filterListByCategory(categoryName: String): List<Product> {
        return productList.filter { product -> product.category.equals(categoryName, true) }
    }

}