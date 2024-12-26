package com.example.codingassignment.ui.screenTwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.codingassignment.R
import com.example.codingassignment.data.roomDb.PhoneNumberEntity
import com.example.codingassignment.ui.main.MainViewModel
import com.example.codingassignment.ui.screenThree.ScreenThreeFragment
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ScreenTwoFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModel()
    private val initialOptions = mutableListOf("Home", "Work", "Other")

    private val selectedValues = mutableListOf<String?>(null, null, null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_screen_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumberFields = listOf(
            view.findViewById<EditText>(R.id.phoneNumOne),
            view.findViewById<EditText>(R.id.phoneNumTwo),
            view.findViewById<EditText>(R.id.phoneNumThree),
            view.findViewById<EditText>(R.id.phoneNumFour),
        )

        val spinners = listOf(
            view.findViewById<Spinner>(R.id.spinnerOne),
            view.findViewById<Spinner>(R.id.spinnerTwo),
            view.findViewById<Spinner>(R.id.spinnerThree),
            view.findViewById<Spinner>(R.id.spinnerFour),
        )

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.my_string_array,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        )

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)


        spinners.forEachIndexed { index, spinnners ->
            spinnners.adapter = adapter
            spinnners.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, position: Int, id: Long
                ) {
                    val selectedValue = parent.getItemAtPosition(position).toString()
                    selectedValues[index] = selectedValue
                    if (index < spinners.size - 1) {
                        val nextSpinner = spinners[index + 1]
                        updateSpinnerOptions(nextSpinner)
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
            if (index == 0) {
                updateSpinnerOptions(spinnners)
            }
        }

        viewModel.phoneNumber.observe(viewLifecycleOwner) { phoneumbers ->
            phoneumbers?.forEachIndexed { index, phone ->
                phoneNumberFields[index].setText(phone)
            }
        }

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val phoneDetails = mutableListOf<PhoneNumberEntity>()
            var otherCount = 0
            spinners.forEachIndexed { index, spinner ->
                val type = when (spinner.selectedItem.toString()) {
                    "Home" -> "Home"
                    "Work" -> "Work"
                    "Other" -> {
                        otherCount++
                        "Other$otherCount"
                    }

                    else -> "Other"
                }
                phoneDetails.add(
                    PhoneNumberEntity(
                        phoneNumber = phoneNumberFields[index].text.toString(), phoneType = type
                    )
                )
            }

            viewModel.savePhoneNumberDetails(phoneDetails)

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ScreenThreeFragment())
                .addToBackStack(null)
                .commit()
        }

    }

    private fun updateSpinnerOptions(spinner: Spinner) {
        val availableOptions = initialOptions.filterNot { selectedValues.contains(it) }

        val newOptions = if ("Other" !in availableOptions) {
            availableOptions + "Other"
        } else {
            availableOptions
        }

        val updatedAdapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            newOptions
        )
        updatedAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        spinner.adapter = updatedAdapter
    }

}
