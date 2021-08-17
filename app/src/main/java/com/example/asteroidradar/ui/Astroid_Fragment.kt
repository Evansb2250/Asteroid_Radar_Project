package com.example.asteroidradar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentAstroidBinding
import com.example.asteroidradar.network.AstroidViewModel
import com.example.asteroidradar.network.viewModelFactor
import com.example.asteroidradar.recyclerview.AstroidAdapter
import com.example.asteroidradar.recyclerview.AstroidListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class Astroid_Fragment : Fragment() {

    lateinit var binding: FragmentAstroidBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        lateinit var adapter: AstroidAdapter
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_astroid_, container, false)

        val fact = viewModelFactor(requireActivity().application)
        val vm = ViewModelProvider(this, fact).get(AstroidViewModel::class.java)

        adapter = AstroidAdapter(AstroidListener { astroidId ->
            Toast.makeText(requireContext(), "you clicked ${astroidId}", Toast.LENGTH_LONG).show()
        })
        binding.recyclerView.adapter = adapter


        vm.neoNasaObject.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })



        return binding.root
    }

    companion object {
    }
}