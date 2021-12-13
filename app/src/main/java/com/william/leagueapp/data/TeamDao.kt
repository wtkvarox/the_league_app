package com.william.leagueapp.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.william.leagueapp.data.model.ItemDataState

@Dao
interface TeamDao {
    @Query("SELECT * FROM Team")
    fun findAllTeams(): List<ItemDataState.Team>

    @Query("SELECT * FROM Team WHERE strLeague IN (:leagueName) OR strLeague3 IN (:leagueName)")
    fun findAllTeamsFromLeague(leagueName: String): List<ItemDataState.Team>

    @Query("SELECT * FROM Team WHERE idTeam IN (:idTeam)")
    fun findTeamLeague(idTeam: Int): ItemDataState.Team?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teams: List<ItemDataState.Team>)
}

@Database(
    entities = [ItemDataState.Team::class],
    version = 1
)
abstract class LeagueDb : RoomDatabase() {
    abstract fun teamsDao(): TeamDao
}