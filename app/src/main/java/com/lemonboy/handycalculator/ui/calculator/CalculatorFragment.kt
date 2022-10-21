package com.lemonboy.handycalculator.ui.calculator

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.CalculatorFragmentBinding
import com.lemonboy.handycalculator.ui.history.HistoryFragment
import com.lemonboy.handycalculator.ui.settings.SettingsActivity
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class CalculatorFragment : Fragment(), MenuProvider {
    private var _binding: CalculatorFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = CalculatorFragmentBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputValues()
        showHistory()
    }

    private fun showHistory() {
        val showHist = binding.showHistory
        showHist.setOnClickListener {
            val intent = Intent(context, HistoryFragment::class.java)
            startActivity(intent)

            //val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            //val navController = navHostFragment.navController

            //val action = CalculatorFragmentDirections.actionNavCalculatorToHistoryFragment()
            //navController.navigate(action)
        }
    }

    private fun inputValues() {
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
                binding.problem.text.toString()
                binding.answer.text.toString()

                binding.answer.text = DecimalFormat("0.######").format(result).toString()
                binding.answer.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))

                //CalculatorViewModel.appendCalculation()
            }

        } catch (e: Exception) {
            // error
            binding.answer.text = ""
            binding.answer.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.nav_settings -> {
                val intent = Intent(context, SettingsActivity::class.java)
                startActivity(intent)

                return true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}