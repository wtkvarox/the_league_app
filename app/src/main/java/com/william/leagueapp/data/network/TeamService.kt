package com.william.leagueapp.data.network

import com.william.leagueapp.data.model.ItemDataState
import com.william.leagueapp.data.model.NextEventModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeamService @Inject constructor(private val api: TeamApiClient) {
    suspend fun getTeamsLeague(league: String): ItemDataState.TeamModel {
        return withContext(Dispatchers.IO) {
            val response = api.getAllTeamsLeague(league)
            response.body() ?: ItemDataState.TeamModel(emptyList())
        }
    }

    suspend fun getNextEvents(idTeam: Int): NextEventModel {
        return withContext(Dispatchers.IO) {
            val response = api.getNextEvents(idTeam)
            response.body() ?: NextEventModel(emptyList())
        }
    }
}