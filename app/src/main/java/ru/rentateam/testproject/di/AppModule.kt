package ru.rentateam.testproject.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.rentateam.testproject.BuildConfig
import ru.rentateam.testproject.data.Repository
import ru.rentateam.testproject.data.local.db.AppDatabase
import ru.rentateam.testproject.data.local.db.DBHelper
import ru.rentateam.testproject.data.remote.retrofit.RentaTeamApi
import ru.rentateam.testproject.data.remote.retrofit.adapter.FlowCallAdapterFactory
import ru.rentateam.testproject.data.remote.retrofit.converter.EnvelopingConverter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(EnvelopingConverter())
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(FlowCallAdapterFactory())
        .baseUrl(BuildConfig.APP_BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RentaTeamApi =
        retrofit.create(RentaTeamApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(rentaTeamApiService: RentaTeamApi, dbHelper: DBHelper): Repository =
        Repository(rentaTeamApiService, dbHelper)

    @Provides
    @Singleton
    fun provideDBHelper(appDatabase: AppDatabase): DBHelper = DBHelper(appDatabase)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "rentaDb")
            .build()

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()
}