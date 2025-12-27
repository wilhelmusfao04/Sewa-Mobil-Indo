// ui/MobilListFragment.kt (update: sync -> Room -> UI)
package com.example.sewamobil.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.sewamobil.Intents
import com.example.sewamobil.R
import com.example.sewamobil.model.Mobil
import com.example.sewamobil.repo.MobilRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MobilListFragment : Fragment() {

    companion object {
        fun newInstance() = MobilListFragment()
    }

    private lateinit var repo: MobilRepository
    private var cached: List<Mobil> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = MobilRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mobil_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 = view.findViewById<Button>(R.id.btnMobil1)
        val btn2 = view.findViewById<Button>(R.id.btnMobil2)
        val btn3 = view.findViewById<Button>(R.id.btnMobil3)

        viewLifecycleOwner.lifecycleScope.launch {
            repo.seedIfEmpty()

            repo.syncMobilFromServer()
                .onFailure { Toast.makeText(requireContext(), "Gagal memuat data server", Toast.LENGTH_SHORT).show() }

            repo.observeMobil().collectLatest { list ->
                cached = list

                val m1 = list.getOrNull(0)
                val m2 = list.getOrNull(1)
                val m3 = list.getOrNull(2)

                btn1.isEnabled = m1 != null
                btn2.isEnabled = m2 != null
                btn3.isEnabled = m3 != null

                btn1.text = m1?.let { "${it.nama} - Lihat Detail" } ?: "Tidak ada data"
                btn2.text = m2?.let { "${it.nama} - Lihat Detail" } ?: "Tidak ada data"
                btn3.text = m3?.let { "${it.nama} - Lihat Detail" } ?: "Tidak ada data"
            }
        }

        btn1.setOnClickListener { cached.getOrNull(0)?.let { openDetail(it) } }
        btn2.setOnClickListener { cached.getOrNull(1)?.let { openDetail(it) } }
        btn3.setOnClickListener { cached.getOrNull(2)?.let { openDetail(it) } }
    }

    private fun openDetail(mobil: Mobil) {
        startActivity(
            Intents.openDetailMobil(
                context = requireContext(),
                nama = mobil.nama,
                harga = mobil.hargaPerHari,
                transmisi = mobil.transmisi,
                kursi = mobil.kursi
            )
        )
    }
}
