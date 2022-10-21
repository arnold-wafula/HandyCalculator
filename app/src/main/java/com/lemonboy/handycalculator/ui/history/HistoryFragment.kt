package com.lemonboy.handycalculator.ui.history

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.adapter.HistoryAdapter
import com.lemonboy.handycalculator.databinding.HistoryFragmentBinding
import com.lemonboy.handycalculator.db.HistoryDatabase
import com.lemonboy.handycalculator.viewmodel.HistoryViewModel
import com.lemonboy.handycalculator.viewmodel.HistoryViewModelFactory

class HistoryFragment: AppCompatActivity() {
    private lateinit var binding: HistoryFragmentBinding

    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = resources.getString(R.string.title_history)
            setDisplayHomeAsUpEnabled(true)
        }

        val application = requireNotNull(this).application
        val dataSource = HistoryDatabase.getInstance(application)
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = HistoryAdapter()
        binding.histRecyclerView.adapter = adapter

        viewModel.historyList.observe(this) { history ->
            history.let {
                adapter.submitList(history)
            }
            Log.d(TAG, "history: $history")
        }

        viewModel.getHistory()
    }

    /*
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "History"
    }*/
}