package com.william.leagueapp.ui.view

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.william.leagueapp.data.model.Result
import com.william.leagueapp.ui.view.ScreenSlidePageFragment.Companion.newInstance

class ScreenSlidePagerAdapter(fm: FragmentManager, private var nextEvents: List<Result>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): ScreenSlidePageFragment {
        return newInstance(nextEvents[position])
    }

    override fun getCount(): Int {
        return nextEvents.size
    }
}