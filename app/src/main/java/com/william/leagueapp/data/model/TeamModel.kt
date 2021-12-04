package com.william.leagueapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamModel(
    val teams: List<Team>,
)

@Entity
data class Team(
    @PrimaryKey
    val idTeam: Int?,
    val idSoccerXML: Int?,
    val idAPIfootball: Int?,
    val intLoved: String?,
    val strTeam: String?,
    val strTeamShort: String?,
    val strAlternate: String?,
    val intFormedYear: Int?,
    val strSport: String?,
    val strLeague: String?,
    val idLeague: Int?,
    val strLeague2: String?,
    val idLeague2: Int?,
    val strLeague3: String?,
    val idLeague3: String?,
    val strLeague4: String?,
    val idLeague4: String?,
    val strLeague5: String?,
    val idLeague5: String?,
    val strLeague6: String?,
    val idLeague6: String?,
    val strLeague7: String?,
    val idLeague7: String?,
    val strDivision: String?,
    val strManager: String?,
    val strStadium: String?,
    val strKeywords: String?,
    val strRSS: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: Int?,
    val strWebsite: String?,
    val strFacebook: String?,
    val strTwitter: String?,
    val strInstagram: String?,
    val strDescriptionEN: String?,
    val strDescriptionDE: String?,
    val strDescriptionFR: String?,
    val strDescriptionCN: String?,
    val strDescriptionIT: String?,
    val strDescriptionJP: String?,
    val strDescriptionRU: String?,
    val strDescriptionES: String?,
    val strDescriptionPT: String?,
    val strDescriptionSE: String?,
    val strDescriptionNL: String?,
    val strDescriptionHU: String?,
    val strDescriptionNO: String?,
    val strDescriptionIL: String?,
    val strDescriptionPL: String?,
    val strGender: String?,
    val strCountry: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
    val strTeamFanart1: String?,
    val strTeamFanart2: String?,
    val strTeamFanart3: String?,
    val strTeamFanart4: String?,
    val strTeamBanner: String?,
    val strYoutube: String?,
    val strLocked: String?
)

data class League(val leagueName: String)