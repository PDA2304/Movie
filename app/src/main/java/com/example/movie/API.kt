package com.example.movie

import okhttp3.*
import java.io.IOException

const val URI: String = "https://api.themoviedb.org/3/movie/"
const val top: String = "top_rated?&api_key=" + BuildConfig.API_KEY
const val expected: String = "upcoming?&api_key=" + BuildConfig.API_KEY

interface API {

    companion object {

         operator fun invoke(boolean: Boolean): Request {
            val client = OkHttpClient()
            val request: Request
            if (boolean) {
                request = Request.Builder().url(URI + top).build()
            } else {
                request = Request.Builder().url(URI + expected).build()
            }

            return request
        }
    }

}