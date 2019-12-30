package com.example.screenking.di

import android.content.Context
import androidx.room.Room
import com.example.screenking.SKApplication
import com.example.screenking.api.DefaultMovieRepo
import com.example.screenking.api.MovieRepo
import com.example.screenking.api.TMDBService
import com.example.screenking.db.MovieDao
import com.example.screenking.db.SKDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {


    companion object {
        private const val BASE_URL_TMDB = "https://api.themoviedb.org/3/"
    }

    @Provides
    fun provideContext(app: SKApplication): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun providesDb(context: Context): SKDatabase {
        return Room.databaseBuilder(context, SKDatabase::class.java, "sk.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(db: SKDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun providesTmdbService(): TMDBService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(TMDBService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepo(tmdbService: TMDBService, movieDao: MovieDao): MovieRepo {
        return DefaultMovieRepo(tmdbService, movieDao)
    }


}