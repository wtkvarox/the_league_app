package com.william.leagueapp.ui.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.william.leagueapp.R
import com.william.leagueapp.data.model.ItemDataState
import kotlinx.android.synthetic.main.item_league.view.img_team_badge
import kotlinx.android.synthetic.main.item_league.view.item
import kotlinx.android.synthetic.main.item_league.view.txt_name
import kotlinx.android.synthetic.main.item_league.view.txt_stadium

class LeaguesAdapter(private val leagues: List<ItemDataState.Team>) :
    RecyclerView.Adapter<LeaguesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = leagues[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_league, parent, false))
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(team: ItemDataState.Team) {
            itemView.txt_name.text = team.strTeam
            itemView.txt_stadium.text = team.strStadium
            itemView.item.setOnClickListener {
                val mIntent = Intent(it.context, DetailViewActivity::class.java)
                mIntent.putExtra("ID", team.idTeam)
                it.context.startActivity(mIntent)
            }

            Glide.with(itemView.context).load(team.strTeamBadge).into(itemView.img_team_badge)
        }
    }
}