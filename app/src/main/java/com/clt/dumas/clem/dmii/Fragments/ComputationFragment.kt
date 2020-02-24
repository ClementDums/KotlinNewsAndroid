package com.clt.dumas.clem.dmii.Fragments

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clt.dumas.clem.dmii.R
import com.clt.dumas.clem.dmii.ViewModel.ComputeViewModel
import com.clt.dumas.clem.dmii.extension.toDouble
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.choice_fragment.*
import kotlinx.android.synthetic.main.computation_fragment.*

class ComputationFragment : Fragment() {

    private lateinit var viewModel: ComputeViewModel

    val operation: Operation by lazy {
        arguments?.getParcelable(ARGS_OPERATION) ?: Operation.SUM
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.computation_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Le view model est rattaché au cycle de vie du fragment
        // Quand le fragment se détruit, le VM se détruit aussi
        viewModel =
            ViewModelProvider(this).get(ComputeViewModel::class.java)
    }

    companion object {
        const val ARGS_OPERATION = "ARGS_OPERATION"
        const val SAVE_OPERATION = "SUM"
        fun newInstance(operation: Operation): ComputationFragment {
            return ComputationFragment().apply {
                //On sauvegarde l’opération dans les arguments,
                //Si le fragment se recrée, la valeur sera restaurée
                arguments = bundleOf(ARGS_OPERATION to operation)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nombre1.addTextChangedListener(watcher)
        nombre2.addTextChangedListener(watcher)
        calcul.setOnClickListener {
            checkOperation()
        }
    }


    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            checkOperation()
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.resultatLiveData.observe(viewLifecycleOwner, Observer {
            result.text = it.toString()

        })
    }

    fun checkOperation() {
        val nb1 = nombre1.toDouble() ?: 0.0
        val nb2 = nombre2.toDouble() ?: 0.0
        when (operation) {
            Operation.SUM -> {
                viewModel.returnSum(nb1, nb2)

            }
            Operation.MINUS -> {
                viewModel.returnMinus(nb1, nb2)
            }
            Operation.PRODUCT -> {
                viewModel.returnProduct(nb1, nb2)

            }
            Operation.DIVISE -> {
                viewModel.returnDiv(nb1, nb2)

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(operation.toString(), SAVE_OPERATION)
        super.onSaveInstanceState(outState)
    }


}

@Parcelize
enum class Operation : Parcelable {
    SUM, PRODUCT, MINUS, DIVISE
}
