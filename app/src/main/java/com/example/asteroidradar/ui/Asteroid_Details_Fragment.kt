package com.example.asteroidradar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.FragmentAsteroidDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Asteroid_Details_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Asteroid_Details_Fragment : Fragment() {
    val args:Asteroid_Details_FragmentArgs by navArgs()
    lateinit var binding:FragmentAsteroidDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_asteroid__details_, container, false)

        binding.astroid = args.asteroid
        binding.lifecycleOwner = this
        // Inflate the layout for this fragment
        return binding.root
    }


}