package com.tapi.viewmodellesson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe

class MainFrg : Fragment() {
    private lateinit var mView: View
    private lateinit var edtT: EditText
    private lateinit var btT: Button
    private val viewModel: MyViewModel by viewModels {
        MyViewmodelFactory(
            1,
            "12",
            "12",
            requireActivity().application
        )
    }
  private val viewModel1: MyViewModel by viewModels ()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getResultData().observe(viewLifecycleOwner, {
            Log.d("TAG", "onViewCreated: $it")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.frg_main, container, false)
        initViews()
        return mView
    }

    private fun initViews() {
        edtT = mView.findViewById(R.id.edt_text)
        btT = mView.findViewById(R.id.bt_text)
        btT.setOnClickListener {
            viewModel.setFilter(edtT.text.toString())
        }
    }
}