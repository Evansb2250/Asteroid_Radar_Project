package com.example.asteroidradar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentAstroidBinding
import com.example.asteroidradar.network.AstroidViewModel
import com.example.asteroidradar.network.viewModelFactor
import com.example.asteroidradar.recyclerview.AstroidAdapter
import com.example.asteroidradar.recyclerview.AstroidListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class Astroid_Fragment : Fragment() {

    lateinit var adapter: AstroidAdapter

    lateinit var binding: FragmentAstroidBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fact = viewModelFactor(requireActivity().application)
        val vm = ViewModelProvider(this, fact).get(AstroidViewModel::class.java)
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_astroid_, container, false)
        lateinit var adapter: AstroidAdapter

        adapter = AstroidAdapter(AstroidListener { astroidId ->
            vm.setAsteroid(astroidId)
            vm.unsetAsteroid()
        })
        binding.recyclerView.adapter = adapter
      //  setUpAdapter()

        vm.asteroidSelected.observe(viewLifecycleOwner, { selectedAsteroid ->
            selectedAsteroid?.let {
                findNavController().navigate(
                    Astroid_FragmentDirections.actionAstroidFragmentToAsteroidDetailsFragment2(
                        it
                    )
                )
            }

        })

        vm.imageOfTheDay.observe(viewLifecycleOwner, { image ->
            image?.let {
                Toast.makeText(requireContext(),"Image Recieved", Toast.LENGTH_LONG).show()
            }

        })


        vm.neoNasaObject.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return binding.root
    }


//    private fun setUpAdapter() {
//        adapter = AstroidAdapter(AstroidListener { astroidId ->
//            vm.setAsteroid(astroidId)
//            vm.unsetAsteroid()
//        })
//        binding.recyclerView.adapter = adapter
//    }


    companion object {
    }
}