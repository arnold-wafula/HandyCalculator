package com.lemonboy.handycalculator.ui.volume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.VolumeFragmentBinding

class VolumeFragment : Fragment() {

    private var _binding: VolumeFragmentBinding? = null
    private val binding get() = _binding!!

    var spinnerPositionTop: Int = 0
    var spinnerPositionBottom: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = VolumeFragmentBinding.inflate(inflater, container, false)

        val volumes = resources.getStringArray(R.array.volumes)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, volumes)
        }

        arrayAdapter?.setDropDownViewResource(R.layout.unit_item)
        with(binding.spinnerVolumeValueTop) {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    spinnerPositionTop = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    view?.let {
                        Snackbar.make(it, "Nothing selected", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        with(binding.spinnerVolumeValueBottom) {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    spinnerPositionBottom = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    view?.let {
                        Snackbar.make(it, "Nothing selected", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.showBottomSheet.setOnClickListener {
            convertVolumes()

            /*
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            val action = WeightFragmentDirections.actionNavWeightToWeightDialog()
            navController.navigate(action)
            */
        }

        return binding.root
    }

    private fun convertVolumes() {

        val input = binding.etVolumeValueTop.text.toString().toDouble()
        val output = binding.txtVolumeValueBottom

        if (spinnerPositionTop == 0) {
            when(spinnerPositionBottom) {
                0 -> output.text = input.toString()
                1 -> output.text = (input * 1).toString()
                2 -> output.text = (input * 0.001).toString()
                3 -> output.text = (input * 0.000001).toString()
            }
        }

        else if (spinnerPositionTop == 1) {
            when(spinnerPositionBottom) {
                0 -> output.text = (input * 1).toString()
                1 -> output.text = (input * 1).toString()
                2 -> output.text = (input * 0.001).toString()
                3 -> output.text = (input * 0.000001).toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}