package com.william.leagueapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.william.leagueapp.data.TeamDao
import com.william.leagueapp.data.model.ItemDataState

@Database(entities = [ItemDataState.Team::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTeamDao(): TeamDao?
}