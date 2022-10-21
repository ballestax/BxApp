package com.bacon57.bxapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.adapters.AdapterViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bacon57.bxapp.databinding.AdapterPresentationBinding

class PresentationAdapter() : RecyclerView.Adapter<PresentationViewHolder>() {

    var presentations = mutableListOf<Presentation>()

    var selectedPosition: Int = 0

    fun setPresentation(presentations: List<Presentation>) {
        this.presentations = presentations.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresentationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPresentationBinding.inflate(inflater, parent, false)
        return PresentationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PresentationViewHolder, position: Int) {
        val ingredient = presentations[position]
        holder.render(ingredient, selectedPosition == position, View.OnClickListener() {
            run {
                this.selectedPosition = position
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return presentations.size
    }
}

class PresentationViewHolder(
    val binding: AdapterPresentationBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun render(
        presentation: Presentation,
        selected: Boolean,
        onClickListener: View.OnClickListener
    ) {
        if (presentation._enabled) {
            binding.tvName.text = presentation.name.uppercase()
            binding.tvPrice.text = presentation.price.toString()
            binding.rbSelect.isChecked =  selected
            binding.rbSelect.setOnClickListener(onClickListener)

        }
    }
}



