package com.william.leagueapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.william.leagueapp.R
import com.william.leagueapp.data.model.Result
import kotlinx.android.synthetic.main.item_league.view.img_team_badge

private const val ARG_PARAM1 = "IMAGE_SLIDE"

class ScreenSlidePageFragment : Fragment() {
    private lateinit var urlImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            urlImage = it.getString(ARG_PARAM1, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)

        val imageView: ImageView = view.findViewById<View>(R.id.img_slide) as ImageView
        Glide.with(imageView.context).load(urlImage).into(imageView)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(nextEvent: Result) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, nextEvent.strThumb)
                }
            }
    }
}