package com.bacon57.bxapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bacon57.bxapp.databinding.FragmentTablesBinding


class TablesFragment : Fragment() {

    private var _binding: FragmentTablesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTablesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


}