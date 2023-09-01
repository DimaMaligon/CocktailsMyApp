package com.example.cocktailsmyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailsmyapp.databinding.ItemTagLayoutBinding


class IngredientAdapter () :
    RecyclerView.Adapter<IngredientAdapter.IngredientHolder>() {
    private var listIngredients = mutableListOf<String>()

    inner class IngredientHolder(val binding: ItemTagLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        with(holder) {
            val ingredient = listIngredients[position]
            binding.apply {
                tagButton.text = ingredient
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val binding =
            ItemTagLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientHolder(binding)
    }

    override fun getItemCount(): Int {
        return listIngredients.size
    }

    fun setIngredientsList(listIngredients: ArrayList<String>) {
        this.listIngredients = listIngredients.toMutableList()
        notifyDataSetChanged()
    }
}