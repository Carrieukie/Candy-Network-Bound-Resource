package com.karis.networkboundresource.ui.fragments.candylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karis.networkboundresource.databinding.ItemCandyBinding
import com.karis.networkboundresource.models.CandyItem

class CandyListAdapter() : ListAdapter<CandyItem, CandyListAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCandyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candyItem = getItem(position)

        holder.binding.apply {
            Glide.with(imageViewCandy)
                .load(candyItem.image)
                .circleCrop()
                .into(imageViewCandy)
            textViewName.text = candyItem.name
            textViewPrice.text = "$ ${candyItem.price}"
        }

        holder.itemView.setOnClickListener{
            val actions = CandyListFragmentDirections.charactersListToCandyDetails(candyItem)
            it.findNavController().navigate(actions)
        }
    }

    class ViewHolder(val binding: ItemCandyBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}
private val DIFF_UTIL = object : DiffUtil.ItemCallback<CandyItem>() {
    override fun areItemsTheSame(oldItem: CandyItem, newItem: CandyItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CandyItem, newItem: CandyItem): Boolean {
        return oldItem == newItem
    }

}