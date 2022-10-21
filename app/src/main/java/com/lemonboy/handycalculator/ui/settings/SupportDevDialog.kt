package com.lemonboy.handycalculator.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lemonboy.handycalculator.databinding.SupportDevFragmentBinding

class SupportDevDialog: BottomSheetDialogFragment() {
    private var _binding: SupportDevFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = SupportDevFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    /*
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.support_settings, rootKey)
    }*/
}