package com.example.cocktailsmyapp.screens.editcocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.adapter.IngredientAdapter
import com.example.cocktailsmyapp.databinding.FragmentEditCocktailBinding
import com.example.cocktailsmyapp.screens.cardcocktail.CardCocktailViewModel

class EditCocktailFragment : Fragment() {
    private lateinit var binding: FragmentEditCocktailBinding
    private val cocktailCardModel: CardCocktailViewModel by activityViewModels()
    private val adapterIngredient = IngredientAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditCocktailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        replaceEditFieldsCocktail()

        binding.apply {
            binding.buttonEditIngredient.setOnClickListener {
                showPopUpDish()
            }
            binding.buttonEdit.setOnClickListener {
                updateNewFieldsCocktail()
                findNavController().navigate(R.id.action_editCocktailFragment_to_mainFragment)
            }
            binding.buttonCancelEdit.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun replaceEditFieldsCocktail() {
        binding.apply {
            val cocktailObject = cocktailCardModel.cocktailObject.value
            imageEdit.setImageURI(cocktailObject.image.toUri())
            titleEditCocktail.setText(cocktailObject.title)
            descriptionEditCocktail.setText(cocktailObject.description)
            recipeEditCocktail.setText(cocktailObject.recipe)
            cocktailCardModel.setIngredientsList(cocktailObject.ingredients.toMutableList())
        }
    }

    private fun updateNewFieldsCocktail() {
        cocktailCardModel.apply {
            val title = binding.titleEditCocktail.text.toString()
            setTitle(title)
            val description = binding.descriptionEditCocktail.text.toString()
            setDescription(description)
            val recipe = binding.recipeEditCocktail.text.toString()
            setRecipe(recipe)

            updateCocktail()
        }
    }

    private fun initRecyclerIngredients() {
        binding.apply {
            val linerManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            tagEditButtonsRecycler.layoutManager = linerManager
            tagEditButtonsRecycler.adapter = adapterIngredient
            adapterIngredient.setIngredientsList(cocktailCardModel.ingredientsList.value as ArrayList<String>)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            cocktailCardModel.ingredientsList.collect {
                initRecyclerIngredients()
            }
        }
    }

    private fun showPopUpDish() {
        val popup = PopUpIngredientEditFragment()
        popup.show((this).parentFragmentManager, R.string.show_popUp.toString())
    }
}