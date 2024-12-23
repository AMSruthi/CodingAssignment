package com.example.codingassignment.ui.screenThree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.codingassignment.R
import com.example.codingassignment.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class ScreenThreeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fetchButton = view.findViewById<Button>(R.id.retrieveButton)
        val displayView = view.findViewById<TextView>(R.id.dataDisplay)
        val deleteButton = view.findViewById<Button>(R.id.deleteButton)

        fetchButton.setOnClickListener {
            viewModel.getPhoneNumberDetails()
            viewModel.phoneNumberDetails.observe(viewLifecycleOwner) { phoneNumberDetails ->
                displayView.text =
                    phoneNumberDetails.joinToString("\n") { "${it.phoneNumber}: ${it.phoneType}" }
            }
        }

        deleteButton.setOnClickListener {
            viewModel.deleteDetails()
        }

    }
}