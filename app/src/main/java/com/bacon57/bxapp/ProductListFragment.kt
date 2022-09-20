package com.bacon57.bxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bacon57.bxapp.databinding.FragmentProductListBinding


class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null

    private val binding get() = _binding!!

    lateinit var viewModel: ProductListViewModel

    private val retrofitService = RetrofitService.getInstance()

    val adapter = ItemAdapter()
    lateinit var filterAdapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(
            this, ProductViewModelFactory(
                ProductsRepository(retrofitService),
                CategoriesRepository(retrofitService)
            )
        ).get(ProductListViewModel::class.java)

        filterAdapter = FilterAdapter(requireContext())
        binding.categoriesList.adapter = filterAdapter

        binding.rvProductsList.adapter = adapter
        binding.rvProductsList.layoutManager = LinearLayoutManager(this.context)
//        binding.rvProductsList.layoutManager = GridLayoutManager(this.context,2)
//

        viewModel.productList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            adapter.setproductList(it)
        })

        viewModel.errorMessage.observe(getViewLifecycleOwner(), Observer {

        })

        viewModel.categoriesList.observe(getViewLifecycleOwner(), Observer {
            Log.d("ProductListFragment", "onCreate: $it")
            filterAdapter.setCategoryList(it)
        })

        viewModel.getAllProducts()
        viewModel.getAllCategories()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}