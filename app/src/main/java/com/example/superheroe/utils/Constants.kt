package com.example.superheroe.utils

import com.example.superheroe.BuildConfig

class Constants {
    companion object {

        private const val apiToken = BuildConfig.API_TOKEN

        const val API_BASE_URL = "https://superheroapi.com/api/$apiToken/"
    }
}