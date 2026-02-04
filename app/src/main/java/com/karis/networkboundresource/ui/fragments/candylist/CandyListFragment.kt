package com.karis.networkboundresource.ui.fragments.candylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.karis.networkboundresource.R
import com.karis.networkboundresource.databinding.FragmentCandylistBinding
import com.karis.networkboundresource.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CandyListFragment : Fragment() {

    private val viewmodel by viewModels<CandyListViewModel>()
    private lateinit var binding: FragmentCandylistBinding

    private var emptyStateView: View? = null
    private var errorStateView: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCandylistBinding.inflate(inflater, container, false)

        val candyadapter = CandyListAdapter()
        binding.candyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = candyadapter
        }

        viewmodel.characters.observe(
                viewLifecycleOwner,
                { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            showErrorState(
                                    resource.error?.localizedMessage ?: "Unknown error occurred"
                            )
                            binding.progressBar.isVisible = false
                            binding.candyList.isVisible = false
                        }
                        is Resource.Loading -> {
                            hideAllStates()
                            binding.progressBar.isVisible = true
                            binding.candyList.isVisible = false
                        }
                        is Resource.Success -> {
                            binding.progressBar.isVisible = false

                            if (resource.data.isNullOrEmpty()) {
                                showEmptyState()
                                binding.candyList.isVisible = false
                            } else {
                                hideAllStates()
                                binding.candyList.isVisible = true
                            }
                        }
                    }
                    candyadapter.submitList(resource.data)
                }
        )

        return binding.root
    }

    private fun showEmptyState() {
        errorStateView?.isVisible = false

        if (emptyStateView == null) {
            val stub = binding.root.findViewById<ViewStub>(R.id.emptyStateStub)
            emptyStateView = stub?.inflate()
        }
        emptyStateView?.isVisible = true
    }

    private fun showErrorState(message: String) {
        emptyStateView?.isVisible = false

        if (errorStateView == null) {
            val stub = binding.root.findViewById<ViewStub>(R.id.errorStateStub)
            errorStateView = stub?.inflate()
            errorStateView?.findViewById<MaterialButton>(R.id.errorStateRetryButton)
                    ?.setOnClickListener {
                        // Retry: Hide error state and show loading
                        hideAllStates()
                        binding.progressBar.isVisible = true
                    }
        }
        errorStateView?.isVisible = true
    }

    private fun hideAllStates() {
        emptyStateView?.isVisible = false
        errorStateView?.isVisible = false
    }

    fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
