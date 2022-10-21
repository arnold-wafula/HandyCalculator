package com.lemonboy.nostalculator.ui.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.lemonboy.nostalculator.R
import com.lemonboy.nostalculator.databinding.WeightFragmentBinding

class WeightFragment : Fragment() {

    private var _binding: WeightFragmentBinding? = null
    private val binding get() = _binding!!

    var spinnerPositionTop: Int = 0
    var spinnerPositionBottom: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = WeightFragmentBinding.inflate(inflater, container, false)

        //arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val weights = resources.getStringArray(R.array.weights)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, weights)
        }

        arrayAdapter?.setDropDownViewResource(R.layout.unit_item)
        with(binding.spinnerWeightValueTop) {
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

        with(binding.spinnerWeightValueBottom) {
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
            convertWeights()

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

    override fun onResume() {
        super.onResume()

        val weights = resources.getStringArray(R.array.weights)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, weights)
        }

        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //R.layout.weight_item, weights
        with(binding.spinnerWeightValueTop) {
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

        with(binding.spinnerWeightValueBottom) {
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
    }

    /*
    private fun noValuesProvided(){
        if (binding.etWeightValueTop.text?.isEmpty() == true && binding.txtWeightValueBottom.text.isEmpty()) {
            view?.let {
                Snackbar.make(it, "Enter values", Snackbar.LENGTH_LONG).show()
            }
        }
    }*/

    private fun convertWeights() {

        val input = binding.etWeightValueTop.text.toString().toDouble()
        val output = binding.txtWeightValueBottom

        if (spinnerPositionTop == 0) {
            when(spinnerPositionBottom) {
                0 -> output.text = input.toString()
                1 -> output.text = (input * 200).toString()
                2 -> output.text = (input * 20).toString()
                3 -> output.text = (input * 2).toString()
                4 -> output.text = (input * 0.2).toString()
                5 -> output.text = (input * 0.02).toString()
                6 -> output.text = (input * 0.002).toString()
                7 -> output.text = (input * 0.0002).toString()
                8 -> output.text = (input * 0.0000002).toString()
                9 -> output.text = (input * 0.007055).toString()
                10 -> output.text = (input * 0.000441).toString()
                11 -> output.text = (input * 0.000031).toString()
                12 -> output.text = (input * 0.000000220462262).toString()
                13 -> output.text = (input * 0.000000196841306).toString()
            }
        }
    }
}