package ru.rentateam.testproject.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.rentateam.testproject.R
import ru.rentateam.testproject.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private val aboutViewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentAboutBinding>(
        inflater,
        R.layout.fragment_about,
        container,
        false
    ).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = aboutViewModel
    }.root
}