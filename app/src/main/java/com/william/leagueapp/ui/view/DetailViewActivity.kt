package com.william.leagueapp.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.william.leagueapp.databinding.ActivityDetailViewBinding
import com.william.leagueapp.ui.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailViewBinding
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        teamViewModel.team.observe(this, { team ->
            binding.txtName.text = team.strTeam
            binding.txtDescription.text = team.strDescriptionES
            binding.txtYearFoundation.text = "Fundado en el año: ${team.intFormedYear.toString()}"

            Glide.with(applicationContext).load(team.strTeamBadge).into(binding.imgTeamBadge)
            Glide.with(applicationContext).load(team.strTeamJersey).into(binding.imgJersey)

            teamViewModel.getNextEvents(team)

            binding.imgFacebook.setOnClickListener {
                launchIntent(team.strFacebook)
            }

            binding.imgInstagram.setOnClickListener {
                launchIntent(team.strInstagram)
            }

            binding.imgTwitter.setOnClickListener {
                launchIntent(team.strTwitter)
            }

            binding.imgYoutube.setOnClickListener {
                launchIntent(team.strYoutube)
            }
        })

        teamViewModel.nextEventsModel.observe(this, {
            binding.viewPagerImages.adapter =
                ScreenSlidePagerAdapter(supportFragmentManager, it.results)
            val dotsIndicator = binding.dotsIndicator
            dotsIndicator.setViewPager(binding.viewPagerImages)
        })

        val idTeam = intent.getIntExtra("ID", 0)
        teamViewModel.getDetailTeam(idTeam)

        binding.imgBack.setOnClickListener {
            finish()
        }
    }

    private fun launchIntent(url: String?) {
        if (url == null || url.isEmpty()) {
            return
        }

        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://$url")
            )
        )
    }
}