package com.william.leagueapp.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mauker.materialsearchview.MaterialSearchView
import com.google.android.material.datepicker.MaterialDatePicker
import com.william.leagueapp.Constants
import com.william.leagueapp.Constants.Companion.LEAGUE_ENGLISH_PREMIER
import com.william.leagueapp.Constants.Companion.LEAGUE_FRENCH
import com.william.leagueapp.Constants.Companion.LEAGUE_SPANISH
import com.william.leagueapp.Constants.Companion.UEFA_CHAMPIONS_LEAGUE
import com.william.leagueapp.R
import com.william.leagueapp.databinding.ActivityMainBinding
import com.william.leagueapp.ui.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var rvLeagues: RecyclerView
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.homeToolbar)

        rvLeagues = binding.recyclerViewLeagues

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        configSwipeRefresh()
        configSearchBar()

        binding.navigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_league_1 -> {
                    teamViewModel.getListTeams(LEAGUE_SPANISH)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_league_2 -> {
                    teamViewModel.getListTeams(LEAGUE_ENGLISH_PREMIER)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_league_3 -> {
                    teamViewModel.getListTeams(LEAGUE_FRENCH)
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_league_4 -> {
                    teamViewModel.getListTeams(UEFA_CHAMPIONS_LEAGUE)
                    return@setOnItemSelectedListener true
                }
            }

            false
        }

        binding.imgCalendar.setOnClickListener(calendarOnClickListener())

        teamViewModel.onCreate(LEAGUE_SPANISH)
        teamViewModel.teamModel.observe(this, {
            val leaguesAdapter = LeaguesAdapter(it.teams)
            rvLeagues.setHasFixedSize(true)
            rvLeagues.layoutManager = LinearLayoutManager(applicationContext)
            rvLeagues.adapter = leaguesAdapter
        })

        teamViewModel.isLoading.observe(this, {
            binding.swipe.isRefreshing = it
        })
    }

    private fun configSwipeRefresh() {
        binding.swipe.setColorSchemeColors(
            getColor(R.color.primaryColor),
            getColor(R.color.cardColor)
        )

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            teamViewModel.getListTeams()
        }
    }

    private fun configSearchBar() {
        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        binding.searchView.setSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewOpened() {
                // Do something once the view is open.
            }

            override fun onSearchViewClosed() {
                // Do something once the view is closed.
            }
        })

        binding.searchView.setOnClearClickListener {
            // Clear clicked
        }

        binding.searchView.adjustTintAlpha(0.8f)
        binding.searchView.setOnVoiceClickedListener {
            // Voice clicked
        }
    }

    private fun calendarOnClickListener() = View.OnClickListener {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Slecciona una fecha")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        datePicker.show(supportFragmentManager, "mTag")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                binding.searchView.openSearch()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}