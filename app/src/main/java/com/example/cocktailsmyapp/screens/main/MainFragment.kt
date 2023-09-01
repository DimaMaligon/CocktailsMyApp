package com.example.cocktailsmyapp.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.adapter.CocktailAdapter
import com.example.cocktailsmyapp.databinding.FragmentMainBinding
import com.example.cocktailsmyapp.domain.models.Cocktail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val mainModel: MainViewModel by activityViewModels()
    private val adapterCocktail = CocktailAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        binding.floatingActionButton.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_mainFragment_to_addCocktailFragment)
        }
    }

    private fun initRecyclerCocktails(listCocktails: ArrayList<Cocktail>) {
        binding.apply {
            val linerManager = GridLayoutManager(activity, 2)
            recyclerCocktails.layoutManager = linerManager
            recyclerCocktails.adapter = adapterCocktail
            adapterCocktail.setCocktailsList(listCocktails)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainModel.getCocktails()
            mainModel.cocktailsList.collect {
                initRecyclerCocktails(it as ArrayList<Cocktail>)
            }
        }
    }
}