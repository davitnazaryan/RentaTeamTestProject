package ru.rentateam.testproject.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.rentateam.testproject.data.model.User

@Dao
interface UserDao {
    // TODO: 14.07.21 get users list
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(usersList: List<User>)
}