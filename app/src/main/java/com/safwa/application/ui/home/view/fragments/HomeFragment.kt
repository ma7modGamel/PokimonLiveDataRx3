package com.safwa.application.ui.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels


import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.safwa.application.databinding.FragmentHomeBinding
import com.safwa.application.ui.home.adapter.PokemonAdapter
import com.safwa.application.ui.home.viewmodel.HomeViewModel
import com.safwa.application.ui.home.viewmodel.HomeViewModel_Factory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    lateinit var adapter: PokemonAdapter
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       // homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()

    }


    private fun getData() {

        homeViewModel.getPokemonFromApi()
        homeViewModel._listPokemon.observe(viewLifecycleOwner,{
            list->
            if(!list.isEmpty()){
                binding.rv.layoutManager=LinearLayoutManager(requireContext())
                adapter=PokemonAdapter()
                adapter.submitList(list)
                binding.rv.adapter=adapter
            }
        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}