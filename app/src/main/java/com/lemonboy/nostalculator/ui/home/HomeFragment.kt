package com.lemonboy.nostalculator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lemonboy.nostalculator.R
import com.lemonboy.nostalculator.databinding.HomeFragmentBinding
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat


class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding

    //private var _binding: FragmentHomeBinding? = null
    //private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputValues()

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    private fun inputValues() {
        //val input = binding.problem.text

        binding.buttonZero.setOnClickListener {
            binding.problem.text = addToInputText("0")
        }

        binding.buttonOne.setOnClickListener {
            binding.problem.text = addToInputText("1")
        }

        binding.buttonTwo.setOnClickListener {
            binding.problem.text = addToInputText("2")
        }

        binding.buttonThree.setOnClickListener {
            binding.problem.text = addToInputText("3")
        }

        binding.buttonFour.setOnClickListener {
            binding.problem.text = addToInputText("4")
        }

        binding.buttonFive.setOnClickListener {
            binding.problem.text = addToInputText("5")
        }

        binding.buttonSix.setOnClickListener {
            binding.problem.text = addToInputText("6")
        }

        binding.buttonSeven.setOnClickListener {
            binding.problem.text = addToInputText("7")
        }

        binding.buttonEight.setOnClickListener {
            binding.problem.text = addToInputText("8")
        }

        binding.buttonNine.setOnClickListener {
            binding.problem.text = addToInputText("9")
        }

        binding.buttonPlus.setOnClickListener {
            binding.problem.text = addToInputText("+")
        }

        binding.buttonMin.setOnClickListener {
            binding.problem.text = addToInputText("-")
        }

        binding.buttonMul.setOnClickListener {
            binding.problem.text = addToInputText("×")
        }

        binding.buttonDiv.setOnClickListener {
            binding.problem.text = addToInputText("÷")
        }

        binding.buttonDot.setOnClickListener {
            binding.problem.text = addToInputText(".")
        }

        binding.buttonDel.setOnClickListener {
            val removeLast = binding.problem.text.toString().dropLast(1)
            binding.problem.text = removeLast
        }

        binding.buttonClear.setOnClickListener {
            binding.problem.text = ""
            binding.answer.text = ""
        }

        binding.buttonEquals.setOnClickListener {
            calculate()
        }
    }

    private fun addToInputText(btnValue: String): String {
        return binding.problem.text.toString() + "" + btnValue
    }

    /*private fun isEmpty() {
        if (binding.problem.text.isEmpty()) {
            Toast.makeText(context, "Empty values", Toast.LENGTH_SHORT).show()
        } else {
            calculate()
        }
    }*/

    private fun getInputExpression(): String {
        var expression = binding.problem.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    // perform calculation and display result
    private fun calculate() {
        try {

            val expression = getInputExpression()
            val result = Expression(expression).calculate()

            // error
            if (result.isNaN()) {
                binding.answer.text = ""
                binding.answer.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            } else {
                binding.answer.text = DecimalFormat("0.######").format(result).toString()
                binding.answer.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            }

        } catch (e: Exception) {
            // error
            binding.answer.text = ""
            binding.answer.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }
}