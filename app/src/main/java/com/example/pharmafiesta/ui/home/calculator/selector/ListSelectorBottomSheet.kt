package com.example.pharmafiesta.ui.home.calculator.selector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pharmafiesta.R
import com.example.pharmafiesta.databinding.LayoutListSelectorBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.pharmafiesta.ui.home.calculator.selector.adapter.AdapterSelector

class ListSelectorBottomSheet(
    private var list: List<String>,
    itemSelected: (String) -> Unit
) : BottomSheetDialogFragment() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        LayoutListSelectorBottomSheetBinding.inflate(
            layoutInflater
        )
    }

    private val adapterSelector by lazy {
        AdapterSelector(itemSelected = {
            itemSelected(it)
            dismiss()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        binding.rvSelector.adapter = adapterSelector
        adapterSelector.submitList(list)
    }

    private fun openFullScreen() {
        val dialog = dialog as BottomSheetDialog
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onStart() {
        super.onStart()
        openFullScreen()
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}