package com.karis.networkboundresource.ui.fragments.candyDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.karis.networkboundresource.R
import com.karis.networkboundresource.databinding.FragmentCandyDetailsBinding
import com.karis.networkboundresource.databinding.FragmentCandylistBinding
import com.karis.networkboundresource.ui.fragments.candylist.CandyListAdapter
import com.karis.networkboundresource.ui.fragments.candylist.CandyListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CandyDetailsFragment : Fragment() {


    private val viewmodel by viewModels<CandyListViewModel>()
    private lateinit var binding : FragmentCandyDetailsBinding
    private val args by navArgs<CandyDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCandyDetailsBinding.inflate(inflater,container,false)

        val candyItem = args.candyitem

        binding.apply {

            textViewCandyName.text = candyItem.name
            textViewCandyDescription.text = candyItem.description
            Glide.with(requireContext())
                .load(candyItem.image)
                .into(imageViewImage)
        }

        return binding.root
    }
}