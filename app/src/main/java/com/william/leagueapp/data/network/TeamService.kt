package com.william.leagueapp.data.network

import com.william.leagueapp.data.model.NextEventModel
import com.william.leagueapp.data.model.TeamModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamService @Inject constructor(private val api: TeamApiClient) {
    suspend fun getTeamsLeague(league: String): TeamModel {
        return withContext(Dispatchers.IO) {
            val response = api.getAllTeamsLeague(league)
            response.body() ?: TeamModel(emptyList())
        }
    }

    suspend fun getNextEvents(idTeam: Int): NextEventModel {
        return withContext(Dispatchers.IO) {
            val response = api.getNextEvents(idTeam)
            response.body() ?: NextEventModel(emptyList())
        }
    }
}