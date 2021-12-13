package com.william.leagueapp.data.network

import com.william.leagueapp.data.model.ItemDataState
import com.william.leagueapp.data.model.NextEventModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApiClient {
    @GET("search_all_teams.php")
    suspend fun getAllTeamsLeague(@Query("l") league: String?): Response<ItemDataState.TeamModel>

    @GET("eventslast.php")
    suspend fun getNextEvents(@Query("id") idTeam: Int): Response<NextEventModel>
}