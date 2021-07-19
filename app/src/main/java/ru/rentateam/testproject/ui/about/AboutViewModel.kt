package ru.rentateam.testproject.ui.about

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.rentateam.testproject.R
import ru.rentateam.testproject.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    app: Application
) : BaseViewModel(app) {

    private val _text = MutableLiveData<String>().apply {
        value = app.getString(R.string.about_msg)
    }
    val text: LiveData<String> = _text
}