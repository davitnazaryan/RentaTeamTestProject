package ru.rentateam.testproject.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

internal fun Fragment.showToast(str: String) {
    Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show()
}