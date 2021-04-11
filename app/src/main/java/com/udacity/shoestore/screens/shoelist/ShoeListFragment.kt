package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShowListLayoutBinding
import com.udacity.shoestore.models.ShoesViewModel

class ShoeListFragment : Fragment() {
    private lateinit var viewModel: ShoesViewModel
    private lateinit var binding: FragmentShoeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListDestinationToDetailsFragment())
        }

        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)
        viewModel.shoeObjects.observe(viewLifecycleOwner, Observer { shoesList ->
            for (shoe in shoesList) {
                val listItemBinding = ShowListLayoutBinding.inflate(layoutInflater, null, false)
                listItemBinding.shoe = shoe
                binding.shoeContainer.addView(listItemBinding.root)
            }
        })

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}