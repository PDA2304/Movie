package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var item = intent.getParcelableExtra<Model>("OBJECK")
        Title.text = item?.title
        vote_average.text = item?.vote_average
        release_date.text = "Дата релиза фильма: ${item?.release_date}"
        description_text.text = item?.overview
        Picasso.with(this)
            .load(URI_IMAGE + item?.poster_path)
            .into(image_poster, object : Callback {
                override fun onSuccess() {
                    if (progressBar != null) {
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onError() {
                    Log.i("this", "")
                }
            })

    }
}
