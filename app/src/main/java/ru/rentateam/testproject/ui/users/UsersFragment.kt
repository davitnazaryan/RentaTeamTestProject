package ru.rentateam.testproject.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.rentateam.testproject.R
import ru.rentateam.testproject.data.model.User
import ru.rentateam.testproject.databinding.FragmentUsersBinding
import ru.rentateam.testproject.utils.showToast

@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.OnItemClickListener {

    companion object {
        const val USER = "user"
    }

    private val usersViewModel: UsersViewModel by hiltNavGraphViewModels(R.id.mobile_navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel.loadUsers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentUsersBinding>(
        inflater,
        R.layout.fragment_users,
        container,
        false
    ).apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = usersViewModel
        with(UsersAdapter(this@UsersFragment)) {
            adapter = this
            usersViewModel.usersList.observe(viewLifecycleOwner, {
                it.let(this::submitList)
            })
        }
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(usersViewModel) {
            toastTextLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    showToast(it)
                }
                this.clearToaster()
            }
        }
    }

    override fun onItemClick(user: User) {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main)
            .navigate(
                R.id.navigation_details,
                bundleOf(USER to user)
            )
    }
}