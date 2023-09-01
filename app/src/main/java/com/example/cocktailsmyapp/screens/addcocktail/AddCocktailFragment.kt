package com.example.cocktailsmyapp.screens.addcocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.adapter.IngredientAdapter
import com.example.cocktailsmyapp.databinding.FragmentAddCocktailBinding

class AddCocktailFragment : Fragment() {
    private lateinit var binding: FragmentAddCocktailBinding
    private val cocktailModel: AddCocktailViewModel by activityViewModels()
    private val adapterIngredient = IngredientAdapter()
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                binding.imageAdd.setImageURI(it)
                cocktailModel.setImage(it.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCocktailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        binding.buttonIngredient.setOnClickListener {
            showPopUpDish()
        }
        binding.buttonSave.setOnClickListener {
            saveCocktails()
        }
        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageAdd.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun saveCocktails() {
        cocktailModel.apply {
            val title = binding.titleAddCocktail.text.toString()
            setTitle(title)
            val description = binding.descriptionAddCocktail.text.toString()
            setDescription(description)
            val recipe = binding.recipeAddCocktail.text.toString()
            setRecipe(recipe)

            addCocktail()
        }
    }

    private fun initRecyclerIngredients() {
        binding.apply {
            val linerManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            tagButtonsRecycler.layoutManager = linerManager
            tagButtonsRecycler.adapter = adapterIngredient
            adapterIngredient.setIngredientsList(cocktailModel.ingredientsList.value as ArrayList<String>)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            cocktailModel.ingredientsList.collect {
                initRecyclerIngredients()
            }
        }
    }

    private fun showPopUpDish() {
        val popup = PopUpIngredientFragment()
        popup.show((this).parentFragmentManager, R.string.show_popUp.toString())
    }

    private fun pickImageFromGallery() {
        permissionLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }
}