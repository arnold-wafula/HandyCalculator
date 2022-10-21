package com.lemonboy.handycalculator.converter

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lemonboy.handycalculator.R
import com.lemonboy.handycalculator.adapter.ConverterAdapter
import com.lemonboy.handycalculator.adapter.ConverterAdapterGrid
import com.lemonboy.handycalculator.databinding.ConverterFragmentBinding

class ConverterFragment: Fragment(), MenuProvider {
    private var _binding: ConverterFragmentBinding? = null
    private val binding get() = _binding!!

    //private lateinit var binding: ConverterFragmentBinding

    private val iconList = mutableListOf<Int>()
    private val titleList = mutableListOf<String>()

    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ConverterFragmentBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        recyclerView = binding.recyclerView

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ConverterAdapter(iconList, titleList)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
        }

        postToList()
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_layout, menu)

        val layoutIcon = menu.findItem(R.id.menu_grid)
        setIcon(layoutIcon)
    }

    private fun chooseLayout() {
        when(isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = ConverterAdapter(iconList, titleList)
            }

            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 2)
                recyclerView.adapter = ConverterAdapterGrid(iconList, titleList)
            }
        }
    }

    private fun setIcon(menuItem: MenuItem) {
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(requireContext(), R.drawable.icon_gridview_round)
            else
                ContextCompat.getDrawable(requireContext(), R.drawable.icon_list_round)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_grid -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(menuItem)

                return true
            }
            else -> {
                super.onOptionsItemSelected(menuItem)
            }
        }
    }

    private fun addToList(icon: Int, title: String) {
        iconList.add(icon)
        titleList.add(title)
    }

    private fun postToList() {
        addToList(R.drawable.icon_weight, resources.getString(R.string.menu_item_weight))
        addToList(R.drawable.icon_volume, resources.getString(R.string.menu_item_volume))
        addToList(R.drawable.icon_length, resources.getString(R.string.menu_item_length))
        addToList(R.drawable.icon_temperature, resources.getString(R.string.menu_item_temperature))
        addToList(R.drawable.icon_energy, resources.getString(R.string.menu_item_energy))
        addToList(R.drawable.icon_currency, resources.getString(R.string.menu_item_currency))
    }
}