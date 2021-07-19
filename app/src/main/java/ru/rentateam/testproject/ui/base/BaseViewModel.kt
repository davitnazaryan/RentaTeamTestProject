package ru.rentateam.testproject.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(
    val app: Application,
) : AndroidViewModel(app) {

    val toastTextLiveData = MutableLiveData<String?>()


    fun makeToaster(resId: Int) {
        toastTextLiveData.postValue(app.getString(resId))
    }

    fun makeToaster(text: String) {
        toastTextLiveData.postValue(text)
    }
    
    fun clearToaster() {
        toastTextLiveData.postValue(null)
    }
}