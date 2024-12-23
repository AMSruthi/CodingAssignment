package com.example.codingassignment.ui.screenOne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.codingassignment.R
import com.example.codingassignment.ui.main.MainViewModel
import com.example.codingassignment.ui.screenTwo.ScreenTwoFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ScreenOneFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_screen_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startButton = view.findViewById<Button>(R.id.fetchButton)
        startButton.setOnClickListener {
            viewModel.fetchPhoneNumbers()
            viewModel.phoneNumber.observe(viewLifecycleOwner) { phoneNumbers ->
                if (phoneNumbers != null) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ScreenTwoFragment()).addToBackStack(null)
                        .commit()
                }
            }

        }

    }
}