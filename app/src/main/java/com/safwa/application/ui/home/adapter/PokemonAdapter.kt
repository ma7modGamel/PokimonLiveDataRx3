package com.safwa.application.ui.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.safwa.application.data.models.Pokemon
import com.safwa.application.databinding.ItemPokemonBinding

//class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

class PokemonAdapter(val changeStatusFavItem: ChangeStatusFavItem) :
    ListAdapter<Pokemon, PokemonAdapter.PokemonViewHolder>(PokemonDiffCallback()) {


    class PokemonViewHolder(
        private val binding: ItemPokemonBinding,
        private val changeStatusFavItem: ChangeStatusFavItem
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            Log.e("TAG", "bind: " + pokemon.name)
            binding.txt.text = pokemon.name
            Glide.with(itemView.context)
                .load(pokemon.url)
                .into(binding.img)
            //binding.fav.isSelected = pokemon.isFavorite
            binding.fav.setOnClickListener { listener ->
                if (binding.fav.isSelected) {
                    binding.fav.isSelected = false
                    changeStatusFavItem.removeFromFav(pokemon, false)
                } else {
                    binding.fav.isSelected = true
                    changeStatusFavItem.addToFav(pokemon, true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, changeStatusFavItem)
    }


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)

        holder.bind(pokemon)


    }

    class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    interface ChangeStatusFavItem {
        fun addToFav(pokemon: Pokemon, isFav: Boolean)
        fun removeFromFav(pokemon: Pokemon, isFav: Boolean)
    }
}