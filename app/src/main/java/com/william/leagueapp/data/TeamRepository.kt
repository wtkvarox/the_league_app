package com.william.leagueapp.data

import com.william.leagueapp.data.model.ItemDataState
import com.william.leagueapp.data.model.NextEventModel
import com.william.leagueapp.data.model.TeamProvider
import com.william.leagueapp.data.network.TeamService
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val api: TeamService,
    private val quoteProvider: TeamProvider,
    private val teamDao: TeamDao
) {

    suspend fun getAllTeamsLeague(league: String): ItemDataState.TeamModel {

        val localData = teamDao.findAllTeamsFromLeague(league)

        return if (localData.isEmpty()) {
            val response = api.getTeamsLeague(league)
            teamDao.insertAllTeams(response.teams)
            quoteProvider.teams = response
            response
        } else {
            ItemDataState.TeamModel(localData)
        }
    }

    suspend fun getNextEvents(idTeam: Int): NextEventModel {
        val response = api.getNextEvents(idTeam)
        quoteProvider.results = response

        return response
    }

    fun getDetailTeamLeague(idTeam: Int): ItemDataState.Team? {
        return teamDao.findTeamLeague(idTeam)
    }
}