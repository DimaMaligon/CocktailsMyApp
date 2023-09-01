package com.example.cocktailsmyapp.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.databinding.ItemCocktailBinding
import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.screens.cardcocktail.CardCocktailViewModel
import com.squareup.picasso.Picasso


class CocktailAdapter constructor(private val fragment: Fragment) :
    RecyclerView.Adapter<CocktailAdapter.CocktailHolder>() {
    private var listCocktails = mutableListOf<Cocktail>()
    private val cocktailModel: CardCocktailViewModel by fragment.activityViewModels()

    inner class CocktailHolder(val binding: ItemCocktailBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onBindViewHolder(holder: CocktailHolder, position: Int) {
        with(holder) {
            val cocktail = listCocktails[position]
            binding.apply {
                Picasso.get().load(cocktail.image)
                    .into(imageView)

                titleCocktail.text = cocktail.title
                imageView.setOnClickListener {
                    cocktailModel.setCocktail(cocktail)
                    NavHostFragment.findNavController(fragment)
                        .navigate(R.id.action_mainFragment_to_cocktailCardFragment)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CocktailAdapter.CocktailHolder {
        val binding =
            ItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CocktailHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCocktails.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCocktailsList(listCocktails: ArrayList<Cocktail>) {
        this.listCocktails = listCocktails.toMutableList()
        Log.d("listItem", "${listCocktails.get(0)}")
        notifyDataSetChanged()
    }
}