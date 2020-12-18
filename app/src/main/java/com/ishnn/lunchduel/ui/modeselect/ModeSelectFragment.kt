package com.ishnn.lunchduel.ui.modeselect

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishnn.lunchduel.R

class ModeSelectFragment : Fragment() {

    companion object {
        fun newInstance() = ModeSelectFragment()
    }

    private lateinit var viewModel: ModeSelectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mode_select, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModeSelectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}