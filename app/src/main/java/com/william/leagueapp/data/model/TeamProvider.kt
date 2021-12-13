package com.william.leagueapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamProvider @Inject constructor() {
    var teams: ItemDataState.TeamModel = ItemDataState.TeamModel(emptyList())
    var results: NextEventModel = NextEventModel(emptyList())
}