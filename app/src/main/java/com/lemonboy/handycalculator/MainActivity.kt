package com.lemonboy.handycalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lemonboy.handycalculator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() /*OnSharedPreferenceChangeListener*/ {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_calculator, R.id.nav_converter, R.id.nav_health,
                R.id.nav_favorites
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /*
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val autoReplyTime = sharedPreferences?.getString(getString(R.string.key_auto_reply_time), "")
        Log.i("MainActivity", "Auto Reply Time: $autoReplyTime")

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    /*
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_settings -> {
                val intent = Intent(baseContext, SettingsActivity::class.java)
                startActivity(intent)

                //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                //val navController = navHostFragment.navController

                //val action = CalculatorFragmentDirections.actionNavCalculatorToSettingsFragment()
                //navController.navigate(action)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == getString(R.string.key_status)) {
            val newStatus = sharedPreferences?.getString(key, "")
            Toast.makeText(this, "New Status: $newStatus", Toast.LENGTH_LONG).show()

            /*Snackbar.make(view, "New Status: $newStatus", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()*/
        }

        if (key == getString(R.string.key_auto_reply)) {
            val autoReply = sharedPreferences?.getBoolean(key, false)

            if (autoReply!!) {
                Toast.makeText(this, "Auto Reply ON", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Auto Reply OFF", Toast.LENGTH_LONG).show()
            }
        }
    }*/

    /**
    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }*/
}