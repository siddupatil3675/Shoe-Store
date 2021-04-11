package com.udacity.shoestore.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoesViewModel

class DetailsFragment : Fragment() {
    private lateinit var viewModel: ShoesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)

        binding.detailsViewModel = viewModel
        binding.shoe = viewModel.shoeItem
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListDestination())
        }

        viewModel.fragmentFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListDestination())
                viewModel.onFinished()
            }
        })

        return binding.root
    }
}