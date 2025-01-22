package com.mulosbron.frostsamurai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class MarketFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Eğer parametre kullanacaksanız buraya
        // arguments?.let { ... }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Simetrik buton
        val btnBuySymmetric = view.findViewById<Button>(R.id.btnBuySymmetric)
        btnBuySymmetric.setOnClickListener {
            // Örnek davranış
            Toast.makeText(requireContext(), "Simetrik şekil eklendi!", Toast.LENGTH_SHORT).show()
            // Gelecekte envantere eklemek veya oyun verilerini güncellemek için logic ekleyebilirsiniz
        }

        // Asimetrik buton
        val btnBuyAsymmetric = view.findViewById<Button>(R.id.btnBuyAsymmetric)
        btnBuyAsymmetric.setOnClickListener {
            // Örnek davranış
            Toast.makeText(requireContext(), "Asimetrik şekil eklendi!", Toast.LENGTH_SHORT).show()
            // Burada da benzer şekilde logic eklenebilir
        }
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
