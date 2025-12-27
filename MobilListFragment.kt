// ui/MobilListFragment.kt
package com.example.sewamobil.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sewamobil.Intents
import com.example.sewamobil.R
import com.example.sewamobil.model.Mobil

class MobilListFragment : Fragment() {

    companion object {
        fun newInstance() = MobilListFragment()
    }

    private val dataMobil = listOf(
        Mobil("Toyota Avanza", 350000, "Manual", 7),
        Mobil("Honda Brio", 300000, "Automatic", 5),
        Mobil("Toyota Innova", 550000, "Automatic", 7)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mobil_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnMobil1).setOnClickListener {
            openDetail(dataMobil[0])
        }
        view.findViewById<Button>(R.id.btnMobil2).setOnClickListener {
            openDetail(dataMobil[1])
        }
        view.findViewById<Button>(R.id.btnMobil3).setOnClickListener {
            openDetail(dataMobil[2])
        }
    }

    private fun openDetail(mobil: Mobil) {
        val ctx = requireContext()
        startActivity(
            Intents.openDetailMobil(
                context = ctx,
                nama = mobil.nama,
                harga = mobil.hargaPerHari,
                transmisi = mobil.transmisi,
                kursi = mobil.kursi
            )
        )
    }
}
