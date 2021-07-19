package ru.rentateam.testproject.data.local.db

import ru.rentateam.testproject.data.model.User
import javax.inject.Inject

class DBHelper @Inject constructor(private val appDatabase: AppDatabase) {

    fun getUsers() {
        appDatabase.userDao().getUsers()
    }

    fun insertUsers(users: List<User>) = appDatabase.userDao().insertUsers(users)
}