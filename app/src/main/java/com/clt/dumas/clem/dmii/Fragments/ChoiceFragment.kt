package com.clt.dumas.clem.dmii.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clt.dumas.clem.dmii.R
import kotlinx.android.synthetic.main.choice_fragment.*

class ChoiceFragment : Fragment() {


    override fun onCreateView
                (inflater: LayoutInflater,
                 container: ViewGroup?,
                 savedInstanceState: Bundle?): View?

    {
        return inflater.inflate(R.layout.choice_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        sumButton.setOnClickListener {
            val computationFragment = ComputationFragment.newInstance(Operation.SUM)
            computeFragment(computationFragment)
        }
        minusButton.setOnClickListener {
            val computationFragment = ComputationFragment.newInstance(Operation.MINUS)

            computeFragment(computationFragment)
        }
        divButton.setOnClickListener {
            val computationFragment = ComputationFragment.newInstance(Operation.DIVISE)

            computeFragment(computationFragment)
        }
        multiplyButton.setOnClickListener {
            val computationFragment = ComputationFragment.newInstance(Operation.PRODUCT)
            computeFragment(computationFragment)
        }
    }

    fun computeFragment(computationFragment: ComputationFragment){
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.choice_fragment_container, computationFragment)
        transaction.commit()
    }
}

