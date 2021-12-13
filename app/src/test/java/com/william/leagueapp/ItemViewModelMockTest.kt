package com.william.leagueapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.william.leagueapp.data.model.ItemDataState
import com.william.leagueapp.domain.GetTeamUseCase
import com.william.leagueapp.ui.viewmodel.TeamViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.powermock.modules.junit4.PowerMockRunner

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
class ItemViewModelMockTest {
     private val serviceUtil: GetTeamUseCase = mock()

    private lateinit var itemViewModel: TeamViewModel

    @Mock
    private lateinit var items: ItemDataState.TeamModel

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coRoutineTestRule = CoroutineTestRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockObserverForTeams = mock<Observer<ItemDataState.TeamModel>>()

    @Before
    fun before() {
        itemViewModel = TeamViewModel(serviceUtil).apply {
            teamModel.observeForever(mockObserverForTeams)
        }
    }

    @Test
    fun testIfHeadersMissing() {
        runBlockingTest {
            Mockito.`when`(serviceUtil.invoke(""))
                .thenReturn(
                    items
                )

            itemViewModel.getListTeams("")

            Mockito.verify(mockObserverForTeams)
            Mockito.verifyNoMoreInteractions(mockObserverForTeams)
        }
    }

    private inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}
