package com.example.cocktailsmyapp.screens.cardcocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.databinding.FragmentCocktailCardBinding

class CocktailCardFragment : Fragment() {
    private lateinit var binding: FragmentCocktailCardBinding
    private val cardCocktailModel: CardCocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCocktailCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val cocktailObject = cardCocktailModel.cocktailObject.value
            imageView2.setImageURI(cocktailObject.image.toUri())
            titleCardCocktail.text = cocktailObject.title
            descriptionCardCocktail.text = cocktailObject.description
            recipeCard.text = cocktailObject.recipe
            ingrediesCocktail.text = with(cocktailObject.ingredients) {
                var newListIngredients = ""
                for (ingredient in this) newListIngredients += "$ingredient \n - \n"
                newListIngredients
            }

            buttonEditCard.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_cocktailCardFragment_to_editCocktailFragment)
            }

            buttonCancelCard.setOnClickListener {
                findNavController().popBackStack()
            }

            buttonDeleteCard.setOnClickListener {
                cardCocktailModel.deleteCocktail()
                findNavController().popBackStack()
            }
        }
    }
}