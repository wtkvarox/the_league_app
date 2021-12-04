package com.william.leagueapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.william.leagueapp.data.model.NextEventModel
import com.william.leagueapp.data.model.Team
import com.william.leagueapp.data.model.TeamModel
import com.william.leagueapp.domain.GetTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamUseCase: GetTeamUseCase
) : ViewModel() {

    val teamModel = MutableLiveData<TeamModel>()
    val nextEventsModel = MutableLiveData<NextEventModel>()
    val team = MutableLiveData<Team>()
    val isLoading = MutableLiveData<Boolean>()
    lateinit var leagueName: String

    fun onCreate(leagueName: String) {
        this.leagueName = leagueName

        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTeamUseCase(leagueName)

            teamModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getListTeams(leagueName: String = "") {

        if (leagueName.isNotEmpty()) {
            this.leagueName = leagueName
        }

        val league = this.leagueName
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTeamUseCase.invoke(league)

            teamModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getNextEvents(team: Team) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getTeamUseCase.invoke(team)

            nextEventsModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getDetailTeam(idTeam: Int) {
        viewModelScope.launch {
            val result = getTeamUseCase.invoke(idTeam)

            if (result != null) team.postValue(result!!)
        }
    }
}