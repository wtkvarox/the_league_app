package com.william.leagueapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamProvider @Inject constructor() {
    var teams: TeamModel = TeamModel(emptyList())
    var results: NextEventModel = NextEventModel(emptyList())
}