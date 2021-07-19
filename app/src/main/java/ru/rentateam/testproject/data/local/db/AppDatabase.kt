package ru.rentateam.testproject.data.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rentateam.testproject.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}