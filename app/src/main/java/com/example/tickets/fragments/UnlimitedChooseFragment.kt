package com.example.tickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tickets.R
import com.example.tickets.adapter.UnlimitedTransportInfoAdapter
import com.example.tickets.data.UnlimitedTransportInfo
import com.example.tickets.utils.goneBottomNavigation
import com.google.android.material.button.MaterialButton

class UnlimitedChooseFragment : Fragment() {

    private lateinit var numberOfDays: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { goneBottomNavigation(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unlimited_choose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numberOfDays = view.findViewById(R.id.number_of_days_in_unlimited_choose_screen)
        numberOfDays.text = arguments?.getString("numberOfDays")
        view.findViewById<MaterialButton>(R.id.back_button_in_unlimited_choose_screen)
            .setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_unlimitedChooseFragment_to_priceFragment,
                    null
                )
            )

        equalsNumberOfDays(view)
        addToUnlimitedTransportInfoRecyclerView(view)
    }


    private fun equalsNumberOfDays(view: View) {
        val days = view.findViewById<TextView>(R.id.text_days)
        if (numberOfDays.text == "1") {
            days.text = getString(R.string.day)
        }
    }

    private fun addToUnlimitedTransportInfoRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.unlimited_transport_list)
        with(recyclerView) {
            layoutManager = GridLayoutManager(
                context,
                2,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
            adapter = UnlimitedTransportInfoAdapter(unlimitedTransportInfoList())
            hasFixedSize()
        }
    }

    private fun unlimitedTransportInfoList() = listOf(
        UnlimitedTransportInfo(
            transportName = getString(R.string.bus),
            icon = R.drawable.icon_bus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.trolleybus),
            icon = R.drawable.icon_trolleybus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.tram),
            icon = R.drawable.icon_tram,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.bus_express),
            icon = R.drawable.icon_express_bus,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.metro),
            icon = R.drawable.icon_metro,
        ),
        UnlimitedTransportInfo(
            transportName = getString(R.string.train_city_lines),
            icon = R.drawable.icon_train_city_lines,
        )
    )

}