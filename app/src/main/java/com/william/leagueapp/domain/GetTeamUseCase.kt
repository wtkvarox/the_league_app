package com.william.leagueapp.domain

import com.william.leagueapp.data.TeamRepository
import com.william.leagueapp.data.model.Team
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(
    private val repository: TeamRepository
) {
    suspend operator fun invoke(leagueName: String) = repository.getAllTeamsLeague(leagueName)
    suspend operator fun invoke(team: Team) = repository.getNextEvents(team.idTeam!!)
    operator fun invoke(idTeam: Int) = repository.getDetailTeamLeague(idTeam)
}