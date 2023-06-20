package com.github.asadej0951.neversitupcurrency.service

import android.os.Build

object Constants {

    const val TIME_CONNECT = 30L

    const val MESSAGE_NO_INTERNET = "กรุณาตรวจสอบการเชื่อมต่ออินเตอร์เน็ต"

    const val  LEVEL_APP_SERVICE = 2

    var TOKEN_JWT_USER = ""
    /*
    LEVEL_APP_SERVICE : 1 Product
    LEVEL_APP_SERVICE : 2 Demo
    */
    private const val API_ENDPOINT_SSL = "http://"
    private const val API_ENDPOINT_HTTPS = "https://"
    const val URL_PRO =  "https://06a2-180-183-104-228.ap.ngrok.io"
    const val URL_DEV = API_ENDPOINT_HTTPS + "demoapi.lumpsum.in.th"

    fun getAndroidVersion(): String {
        val release = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT
        return "Android SDK: $sdkVersion ($release)"
    }

}