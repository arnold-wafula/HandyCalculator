package com.lemonboy.handycalculator.ui.energy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lemonboy.handycalculator.databinding.EnergyFragmentBinding

class EnergyFragment : Fragment() {
    private var _binding: EnergyFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = EnergyFragmentBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }
}