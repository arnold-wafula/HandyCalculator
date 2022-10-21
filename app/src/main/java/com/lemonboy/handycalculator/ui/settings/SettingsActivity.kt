package com.lemonboy.handycalculator.ui.settings

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.lemonboy.handycalculator.MainActivity
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.databinding.SettingsActivityBinding

class SettingsActivity: AppCompatActivity() {
    private lateinit var binding: SettingsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = resources.getString(R.string.settings)
            setDisplayHomeAsUpEnabled(true)
        }

        supportFragmentManager.beginTransaction().replace(R.id.idFrameLayout, SettingsFragment()).commit()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                MainActivity()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}