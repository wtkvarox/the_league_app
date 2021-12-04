package com.william.leagueapp.di.module

import android.content.Context
import javax.inject.Singleton

import androidx.room.Room
import com.william.leagueapp.Constants
import com.william.leagueapp.data.LeagueDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideLeagueDb(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        LeagueDb::class.java,
        Constants.NAME_DATA_BASE
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideTeamDao(db: LeagueDb) = db.teamsDao()
}