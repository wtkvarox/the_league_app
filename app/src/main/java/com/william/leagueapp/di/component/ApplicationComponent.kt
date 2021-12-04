package com.william.leagueapp.di.component

import android.app.Application
import android.content.Context
import com.william.leagueapp.di.DatabaseInfo
import com.william.leagueapp.ui.view.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext

public interface ApplicationComponent {
    fun inject(mainActivity: MainActivity?)

    @ApplicationContext
    fun getContext(): Context?

    fun getApplication(): Application?

    @DatabaseInfo
    fun getDatabaseName(): String?
}