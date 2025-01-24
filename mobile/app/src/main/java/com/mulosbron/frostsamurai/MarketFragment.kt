package com.mulosbron.frostsamurai

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class MarketFragment : Fragment() {

    private lateinit var tvShurikenCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // arguments?.let { ... }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShurikenCount = view.findViewById(R.id.tvShurikenCount)

        val btnBuySymmetric = view.findViewById<Button>(R.id.btnBuySymmetric)
        btnBuySymmetric.setOnClickListener {
            Toast.makeText(requireContext(), "Simetrik şekil eklendi!", Toast.LENGTH_SHORT).show()
        }

        val btnBuyAsymmetric = view.findViewById<Button>(R.id.btnBuyAsymmetric)
        btnBuyAsymmetric.setOnClickListener {
            Toast.makeText(requireContext(), "Asimetrik şekil eklendi!", Toast.LENGTH_SHORT).show()
        }

        val btnBuyPackage1 = view.findViewById<Button>(R.id.btnBuyPackage1)
        btnBuyPackage1.setOnClickListener {
            Toast.makeText(requireContext(), "Eğitim Paketi 1 satın alındı!", Toast.LENGTH_SHORT).show()
        }

        val btnBuyPackage2 = view.findViewById<Button>(R.id.btnBuyPackage2)
        btnBuyPackage2.setOnClickListener {
            Toast.makeText(requireContext(), "Eğitim Paketi 2 satın alındı!", Toast.LENGTH_SHORT).show()
        }

        showShurikenFromSharedPref()
    }

    private fun showShurikenFromSharedPref() {
        val prefs = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val shurikenValue = prefs.getInt("shuriken", 0)
        tvShurikenCount.text = "Shuriken: $shurikenValue"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MarketFragment().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }
}
