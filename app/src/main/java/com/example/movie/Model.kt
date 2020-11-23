package com.example.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model(
    val vote_average: String,
    val title: String,
    val release_date: String,
    val poster_path: String,
    val overview: String
):Parcelable{}