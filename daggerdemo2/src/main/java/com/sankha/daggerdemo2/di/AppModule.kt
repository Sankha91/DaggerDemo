package com.sankha.daggerdemo2.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.sankha.daggerdemo2.MyApplication
import com.sankha.daggerdemo2.R
import com.sankha.daggerdemo2.db.WordDao
import com.sankha.daggerdemo2.db.WordRoomDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGlide(application: Application) : RequestManager {
        return Glide.with(application)
    }

    @Provides
    fun provideDrawable(application: Application) : Drawable {
        return ContextCompat.getDrawable(application, R.drawable.ic_androidrobot)!!
    }

    @Singleton
    @Provides
    fun provideSharedPreference(application: Application) : SharedPreferences{
        return application.getSharedPreferences(application.packageName, MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideEditor(sharedPreferences: SharedPreferences) : SharedPreferences.Editor{
        return sharedPreferences.edit()
    }

    @Singleton
    @Provides
    fun provideOkhttpClient() : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application) : WordRoomDatabase{
        return Room.databaseBuilder(application, WordRoomDatabase::class.java, "word_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(wordRoomDatabase: WordRoomDatabase): WordDao{
        return wordRoomDatabase.wordDao()
    }

}