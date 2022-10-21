package com.lemonboy.handycalculator.ui.currency

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.CurrencyFragmentBinding
import com.lemonboy.handycalculator.helper.Endpoints
import com.lemonboy.handycalculator.helper.Resource
import com.lemonboy.handycalculator.helper.Utility
import com.lemonboy.handycalculator.model.Rates
import com.lemonboy.handycalculator.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CurrencyFragment : Fragment() {
    private var _binding: CurrencyFragmentBinding? = null
    private val binding get() = _binding!!

    //var baseCurrency = "EUR"
    //var convertedToCurrency = "USD"
    //var conversionRate = 0f

    private var spinnerSelectedItemTop: String? = "AFN"
    private var spinnerSelectedItemBottom: String? = "AFN"

    //ViewModel
    //private val mainViewModel: MainViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = CurrencyFragmentBinding.inflate(layoutInflater, container, false)

        //Initialize both Spinner
        spinnerSetup()

        //Listen to click events
        setUpClickListener()

        return binding.root
    }

    private fun spinnerSetup() {
        val spinnerTopCountry = binding.spnFirstCountry
        spinnerTopCountry.setItems( getAllCountries() )
        /**
        spinnerTopCountry.setOnClickListener {
            Utility.hideKeyboard(this)
        }*/

        spinnerTopCountry.setOnItemSelectedListener { _, _, _, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            spinnerSelectedItemTop = currencySymbol
            binding.txtFirstCurrencyName.text = spinnerSelectedItemTop
        }

        val spinnerBottomCountry = binding.spnSecondCountry
        spinnerBottomCountry.setItems( getAllCountries() )
        /**
        spinnerTopCountry.setOnClickListener {
            Utility.hideKeyboard(this)
        }*/

        spinnerBottomCountry.setOnItemSelectedListener { _, _, _, item ->
            //Set the currency code for each country as hint
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            spinnerSelectedItemBottom = currencySymbol
            binding.txtSecondCurrencyName.text = spinnerSelectedItemBottom
        }
    }

    /**
     * A method for getting a country's currency symbol from the country's code
     * e.g USA - USD
     */
    private fun getSymbol(countryCode: String?): String {
        val availableLocales = Locale.getAvailableLocales()

        for (i in availableLocales.indices) {
            if (availableLocales[i].country == countryCode)
                return Currency.getInstance(availableLocales[i]).currencyCode
        }
        return ""
    }

    /**
     * A method for getting a country's code from the country name
     * e.g Nigeria - NG
     */
    private fun getCountryCode(countryName: String) = Locale.getISOCountries().find { Locale("", it).displayCountry == countryName }

    private fun getAllCountries(): ArrayList<String> {
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' '}.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        countries.sort()

        return countries
    }

    private fun setUpClickListener() {
        //Convert button clicked - check for empty string and internet then do the conversion
        binding.btnConvert.setOnClickListener {

            //check if the input is empty
            val numberToConvert = binding.etFirstCurrency.text.toString()

            if(numberToConvert.isEmpty() || numberToConvert == "0") {
                context?.let { it1 -> ContextCompat.getColor(it1, R.color.dark_red) }?.let { it2 ->
                    Snackbar.make(binding.mainLayout,"Input a value in the first text field, result will be shown in the second text field", Snackbar.LENGTH_LONG)
                        .withColor(it2)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        .show()
                }
            }

            //check if internet is available
            else if (!Utility.isNetworkAvailable(context)) {
                context?.let { it1 -> ContextCompat.getColor(it1, R.color.dark_red) }?.let { it2 ->
                    Snackbar.make(binding.mainLayout,"You are not connected to the internet", Snackbar.LENGTH_LONG)
                        .withColor(it2)
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        .show()
                }
            }

            //carry on and convert the value
            else {
                doConversion()
            }
        }
    }

    /**
     * A method that does the conversion by communicating with the API - fixer.io based on the data inputted
     * Uses viewModel and flows
     */
    private fun doConversion() {
        //hide keyboard
        Utility.hideKeyboard(requireActivity())

        //make progress bar visible
        binding.prgLoading.visibility = View.VISIBLE

        //make button invisible
        binding.btnConvert.visibility = View.GONE

        //Get the data input
        val apiKey = Endpoints.API_KEY
        val from = spinnerSelectedItemTop.toString()
        val to = spinnerSelectedItemBottom.toString()
        val amount = binding.etFirstCurrency.text.toString().toDouble()

        //do the conversion
        mainViewModel.getConvertedData(apiKey, from, to, amount)

        //observe for changes in UI
        observeUi()
    }

    /**
     * Using coroutines flow, changes are observed and responses gotten from the API
     *
     */

    @SuppressLint("SetTextI18n")
    private fun observeUi() {
        mainViewModel.data.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    if (result.data?.status == "success") {
                        val map: Map<String, Rates>
                        map = result.data.rates

                        map.keys.forEach {
                            val rateForAmount = map[it]?.rate_for_amount
                            mainViewModel.convertedRate.value = rateForAmount

                            //format the result obtained e.g 1000 = 1,000
                            val formattedString =
                                String.format("%,.2f", mainViewModel.convertedRate.value)

                            //set the value in the second edit text field
                            binding.etSecondCurrency.setText(formattedString)
                        }

                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.btnConvert.visibility = View.VISIBLE
                    } else if (result.data?.status == "fail") {
                        val layout = binding.mainLayout
                        Snackbar.make(
                            layout,
                            "Oops! something went wrong, Try again",
                            Snackbar.LENGTH_LONG
                        )
                            .withColor(ContextCompat.getColor(requireContext(), R.color.dark_red))
                            .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                            .show()

                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.btnConvert.visibility = View.VISIBLE
                    }
                }

                Resource.Status.ERROR -> {
                    val layout = binding.mainLayout
                    Snackbar.make(
                        layout,
                        "Oops! Something went wrong, Try again",
                        Snackbar.LENGTH_LONG
                    )
                        .withColor(ContextCompat.getColor(requireContext(), R.color.dark_red))
                        .setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        .show()

                    //stop progress bar
                    binding.prgLoading.visibility = View.GONE
                    //show button
                    binding.btnConvert.visibility = View.VISIBLE
                }

                Resource.Status.LOADING -> {
                    //stop progress bar
                    binding.prgLoading.visibility = View.VISIBLE
                    //show button
                    binding.btnConvert.visibility = View.GONE
                }
            }
        }
    }

    /**
     * Method for changing the background color of snackBars
     */
    private fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
        this.view.setBackgroundColor(colorInt)
        return this
    }

    /*
    @OptIn(DelicateCoroutinesApi::class)
    private fun getApiResult() {
        if (binding.etCurrencyValueTop.text?.isNotEmpty() == true && binding.etCurrencyValueTop.text!!.isNotBlank()) {
            //xPhuZ3EDxzW8x6XiWQOvcvwRLOXUzXGz
            //f2cb81036e125ccb57b76a1dc5d94a28784ad327
            val API_KEY = "https://api.ratesapi.io/api/latest?base=$baseCurrency&symbols=$convertedToCurrency"

            if (baseCurrency == convertedToCurrency) {
                view?.let {
                    Snackbar.make(it, "Cannot convert same currency", Snackbar.LENGTH_SHORT).show()
                }
            }

            else {
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val apiResult = URL(API_KEY).readText()
                        val jsonObject = JSONObject(apiResult)

                        conversionRate = jsonObject.getJSONObject("rates").getString(convertedToCurrency).toFloat()
                        Log.d("CurrencyFragment", "$conversionRate")
                        Log.d("CurrencyFragment", apiResult)

                        withContext(Dispatchers.Main) {
                            val currencyValue = ((binding.etCurrencyValueTop.toString().toFloat()) * conversionRate).toString()
                            binding.etCurrencyValueBottom.setText(currencyValue)
                        }
                    }

                    catch (e: Exception) {
                        Log.e("CurrencyFragment", "$e")
                    }
                }
            }
        }
    }
    */

    /*
    private fun spinnerSetup() {
        val currencies = resources.getStringArray(R.array.currencies)
        val arrayAdapter = context?.let {
            ArrayAdapter(it, R.layout.unit_item, currencies)
        }

        arrayAdapter?.setDropDownViewResource(R.layout.unit_item)
        with(binding.spinnerCurrencyValueTop) {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    baseCurrency = position.toString()
                    getApiResult()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    view?.let {
                        Snackbar.make(it, "Nothing selected", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        with(binding.spinnerCurrencyValueBottom) {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    convertedToCurrency = position.toString()
                    getApiResult()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    view?.let {
                        Snackbar.make(it, "Nothing selected", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    */

    /*
    private fun textChanged() {
        with(binding.etCurrencyValueTop) {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    try {
                        getApiResult()
                    } catch (e: Exception) {
                        Log.e("CurrencyFragment", "$e")
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }
    */

}