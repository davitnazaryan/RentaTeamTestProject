package ru.rentateam.testproject.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "users")
data class User(
    @Json(name = "id")
   @PrimaryKey
   @ColumnInfo(name = "id")
    val id: Int,
    @Json(name = "email")
    @ColumnInfo(name = "email")
    val email: String,
    @Json(name = "first_name")
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @Json(name = "avatar")
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
): Parcelable