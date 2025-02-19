package com.safwa.application.ui.home.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.safwa.application.data.models.Pokemon
import com.safwa.application.databinding.FragmentHomeBinding
import com.safwa.application.ui.home.adapter.PokemonAdapter
import com.safwa.application.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() , PokemonAdapter.ChangeStatusFavItem {
    val viewModel: HomeViewModel by viewModels ()
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

        initView()
        getData()

    }

    private fun initView() {
        adapter=PokemonAdapter(this)
        binding.rv.layoutManager=LinearLayoutManager(context)
        binding.rv.adapter=adapter
        binding.rv.setHasFixedSize(true)
    }


    private fun getData() {

        viewModel.getPokemonFromApi()
        viewModel._listPokemon.observe(viewLifecycleOwner,{ list->
            Log.e("TAG", "getData: "+Gson().toJson(list) )
            if(!list.isNullOrEmpty()){
                adapter.submitList(ArrayList(list)) // استخدم نسخة جديدة لتحديث الـ Adapter
            }
        })

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addToFav(pokemon: Pokemon, isFav: Boolean) {
        viewModel.addItemToFavorite(pokemon,isFav)
    }

    override fun removeFromFav(pokemon: Pokemon, isFav: Boolean) {
        TODO("Not yet implemented")
    }
}