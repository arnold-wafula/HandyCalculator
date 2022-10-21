package com.lemonboy.handycalculator.helper

import com.lemonboy.handycalculator.BuildConfig

class Endpoints {

    companion object {

        //Base URL
        const val BASE_URL = "https://api.getgeoapi.com/api/v2/currency/"

        //API KEY
        const val API_KEY = BuildConfig.API_TOKEN

        //COVERT URL
        const val  CONVERT_URL = "convert"
    }
}