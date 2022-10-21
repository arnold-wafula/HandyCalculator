package com.lemonboy.handycalculator.ui.length

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.LengthFragmentBinding

class LengthFragment : Fragment() {
    private var _binding: LengthFragmentBinding? = null
    private val binding get() = _binding!!

    var spinnerPositionTop: Int = 0
    var spinnerPositionBottom: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = LengthFragmentBinding.inflate(inflater, container, false)

        val lengths = resources.getStringArray(R.array.lengths)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, lengths)
        }

        arrayAdapter?.setDropDownViewResource(R.layout.unit_item)
        with(binding.spinnerLengthValueTop) {
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

        with(binding.spinnerLengthValueBottom) {
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
            convertLengths()

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

    private fun convertLengths() {
        val input = binding.etLengthValueTop.text.toString().toDouble()
        val output = binding.txtLengthValueBottom

        if (spinnerPositionTop == 0) {
            when(spinnerPositionBottom) {
                0 -> output.text = input.toString()
                1 -> output.text = (input * 0.1).toString()
                2 -> output.text = (input * 0.001).toString()
                3 -> output.text = (input * 0.000001).toString()
                4 -> output.text = (input * 0.03937).toString()
            }
        }

        else if (spinnerPositionTop == 1) {
            when(spinnerPositionBottom) {
                0 -> output.text = (input * 10).toString()
                1 -> output.text = (input * 1).toString()
                2 -> output.text = (input * 0.01).toString()
                3 -> output.text = (input * 0.00001).toString()
                4 -> output.text = (input * 0.393701).toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}