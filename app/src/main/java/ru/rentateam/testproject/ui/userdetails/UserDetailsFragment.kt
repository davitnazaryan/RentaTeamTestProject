package ru.rentateam.testproject.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ru.rentateam.testproject.MainActivity
import ru.rentateam.testproject.R
import ru.rentateam.testproject.data.model.User
import ru.rentateam.testproject.databinding.FragmentUserDetailsBinding
import ru.rentateam.testproject.ui.users.UsersAdapter
import ru.rentateam.testproject.ui.users.UsersFragment


class UserDetailsFragment : Fragment() {

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = arguments?.getParcelable<User>(UsersFragment.USER)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View= DataBindingUtil.inflate<FragmentUserDetailsBinding>(
    inflater,
    R.layout.fragment_user_details,
    container,
    false
    ).apply {
        lifecycleOwner = viewLifecycleOwner
        user = this@UserDetailsFragment.user
        (requireActivity() as MainActivity).supportActionBar?.title = user?.firstName
    }.root
}