package ru.rentateam.testproject.data

import kotlinx.coroutines.flow.Flow
import ru.rentateam.testproject.data.local.db.DBHelper
import ru.rentateam.testproject.data.model.User
import ru.rentateam.testproject.data.remote.retrofit.RentaTeamApi
import javax.inject.Inject

class Repository @Inject constructor(
    val rentaTeamApi: RentaTeamApi,
    val dbHelper: DBHelper
) {
    suspend fun getUsers(): Flow<List<User>> = rentaTeamApi.users()
}