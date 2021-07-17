package com.karis.networkboundresource.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.karis.networkboundresource.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private val viewmodel by viewModels<CharactersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        viewmodel.characters.observe(viewLifecycleOwner,{
              Toast.makeText(requireContext(), it.data.toString()   , Toast.LENGTH_SHORT).show()
        })


        return inflater.inflate(R.layout.fragment_characters_list, container, false)
    }


}