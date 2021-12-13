package com.william.leagueapp.domain

import com.william.leagueapp.data.TeamRepository
import com.william.leagueapp.data.model.ItemDataState
import javax.inject.Inject

open class GetTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(leagueName: String) = repository.getAllTeamsLeague(leagueName)
    suspend operator fun invoke(team: ItemDataState.Team) = repository.getNextEvents(team.idTeam!!)
    operator fun invoke(idTeam: Int) = repository.getDetailTeamLeague(idTeam)
}