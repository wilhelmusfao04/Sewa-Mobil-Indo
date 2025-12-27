// ui/InfoFragment.kt
package com.example.sewamobil.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sewamobil.R

class InfoFragment : Fragment() {

    companion object {
        private const val ARG_NAMA = "arg_nama"
        private const val ARG_TRANSMISI = "arg_transmisi"
        private const val ARG_KURSI = "arg_kursi"

        fun newInstance(nama: String, transmisi: String, kursi: Int): InfoFragment {
            return InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAMA, nama)
                    putString(ARG_TRANSMISI, transmisi)
                    putInt(ARG_KURSI, kursi)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nama = arguments?.getString(ARG_NAMA).orEmpty()
        val transmisi = arguments?.getString(ARG_TRANSMISI).orEmpty()
        val kursi = arguments?.getInt(ARG_KURSI) ?: 0

        view.findViewById<TextView>(R.id.tvInfoTitle).text = nama
        view.findViewById<TextView>(R.id.tvInfoDetail).text =
            "Transmisi: $transmisi\nJumlah Kursi: $kursi"
    }
}
