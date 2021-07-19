package ru.rentateam.testproject.ui.users

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.rentateam.testproject.R
import ru.rentateam.testproject.data.Repository
import ru.rentateam.testproject.data.model.User
import ru.rentateam.testproject.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    val repo: Repository,
    application: Application
) : BaseViewModel(application) {

    val usersList = MutableLiveData<List<User>>()
    val loading = MutableLiveData<Boolean>()

    fun loadUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            repo.getUsers()
                .catch {
                    loading.postValue(false)
                    makeToaster(R.string.internal_error)
                }.collect {
                    loading.postValue(false)
                    usersList.postValue(it)
                }
        }
    }

    fun onRefresh() {
        loadUsers()
    }
}