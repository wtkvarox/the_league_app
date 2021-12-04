package com.william.leagueapp.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.william.leagueapp.data.model.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM Team")
    fun findAllTeams(): List<Team>

    @Query("SELECT * FROM Team WHERE strLeague IN (:leagueName) OR strLeague3 IN (:leagueName)")
    fun findAllTeamsFromLeague(leagueName: String): List<Team>

    @Query("SELECT * FROM Team WHERE idTeam IN (:idTeam)")
    fun findTeamLeague(idTeam: Int): Team?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teams: List<Team>)
}

@Database(
    entities = [Team::class],
    version = 1
)
abstract class LeagueDb : RoomDatabase() {
    abstract fun teamsDao(): TeamDao
}

/*private fun saveTeams(teams: List<Team>) {
    doAsync {
        val room: TeamsDb = Room
            .databaseBuilder(applicationContext, TeamsDb::class.java, "teamss")
            .build()

        room.teamsDao().insertAllTeams(teams)
        val people = room.teamsDao().findAllTeams()
    }
}*/