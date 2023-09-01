package com.example.cocktailsmyapp.screens.addcocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.cocktailsmyapp.databinding.FragmentPopUpIngredientBinding


class PopUpIngredientFragment : DialogFragment() {
    private lateinit var binding: FragmentPopUpIngredientBinding
    private val cocktailModel: AddCocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopUpIngredientBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonAddIngredient.setOnClickListener {
                val newListIngredients = cocktailModel.ingredientsList.value.toMutableList()
                val newIngredient: String = ingredientName.text.toString()

                newListIngredients.add(newIngredient)
                cocktailModel.setIngredientsList(newListIngredients)

                buttonCancelPopUp.setOnClickListener {
                    dismiss()
                }
            }
        }
    }
}