package ru.rentateam.testproject.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.rentateam.testproject.R
import ru.rentateam.testproject.databinding.FragmentAboutBinding
@AndroidEntryPoint
class AboutFragment : Fragment() {

    private val aboutViewModel: AboutViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

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