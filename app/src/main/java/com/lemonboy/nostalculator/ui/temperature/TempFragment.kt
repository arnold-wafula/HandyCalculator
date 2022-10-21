package com.lemonboy.nostalculator.ui.temperature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.lemonboy.nostalculator.R
import com.lemonboy.nostalculator.databinding.TempFragmentBinding

class TempFragment : Fragment() {

    private var _binding: TempFragmentBinding? = null
    private val binding get() = _binding!!

    var spinnerPositionTop: Int = 0
    var spinnerPositionBottom: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = TempFragmentBinding.inflate(inflater, container, false)

        val temps = resources.getStringArray(R.array.temps)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, temps)
        }

        arrayAdapter?.setDropDownViewResource(R.layout.unit_item)
        with(binding.spinnerTempValueTop) {
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

        with(binding.spinnerTempValueBottom) {
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
            convertTemps()

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

    private fun convertTemps() {
        val input = binding.etTempValueTop.text.toString().toDouble()
        val output = binding.txtTempValueBottom

        /***
         *  Celsius to Kelvin: K = C + 273.15.
            Celsius to Fahrenheit: F = C(9/5) + 32.
            Fahrenheit to Celsius: C = (F-32) (5/9)
            Fahrenheit to Kelvin: K = (F-32) (5/9) + 273.15.
            Kelvin to Celsius: C = K - 273.15.
            Kelvin to Fahrenheit: F = (K-273.15) (9/5) + 32.
         */

        when(spinnerPositionTop) {
            0 ->
                when(spinnerPositionBottom) {
                    0 -> output.text = input.toString()
                    1 -> output.text = (input * 9/5 + 32).toString()
                    2 -> output.text = (input + 273.15).toString()
                }

            1 ->
                when(spinnerPositionBottom) {
                    0 -> output.text = ((input-32) * 5/9).toString()
                    1 -> output.text = input.toString()
                    2 -> output.text = ((input-32) * 5/9 + 273.15).toString()
                }

            2 ->
                when(spinnerPositionBottom) {
                    0 -> output.text = (input - 273.15).toString()
                    1 -> output.text = ((input-273.15) * 9/5 + 32).toString()
                    2 -> output.text = input.toString()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}