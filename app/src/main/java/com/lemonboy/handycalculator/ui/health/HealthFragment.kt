package com.lemonboy.handycalculator.ui.health

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.CalculatorFragmentBinding
import com.lemonboy.handycalculator.databinding.HealthFragmentBinding
import com.lemonboy.handycalculator.ui.settings.SettingsActivity

class HealthFragment: Fragment(), MenuProvider {
    private var _binding: HealthFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HealthFragmentBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}