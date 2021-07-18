package com.karis.networkboundresource.ui.fragments.candylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.networkboundresource.databinding.FragmentCandylistBinding
import com.karis.networkboundresource.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CandyListFragment : Fragment() {

    private val viewmodel by viewModels<CandyListViewModel>()
    private lateinit var binding : FragmentCandylistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCandylistBinding.inflate(inflater,container,false)

        val candyadapter = CandyListAdapter()
        binding.candyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = candyadapter
        }

        viewmodel.characters.observe(viewLifecycleOwner,{ resource ->
            when(resource){
                is Resource.Error -> {
                    toast(resource.error?.localizedMessage.toString())
                    binding.progressBar.isVisible = false

                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressBar.isVisible = false

                }
            }
             candyadapter.submitList(resource.data)
        })


        return binding.root
    }

    fun toast(message : String){
        Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
    }

}