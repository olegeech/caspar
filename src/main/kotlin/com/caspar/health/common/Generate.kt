package com.caspar.health.common
import java.util.Random

object Generate {

    fun phoneNumber(withCountryCode: String = "+380"): String {
        val phoneDigits = Random().nextInt(9000000) + 1000000
        //return "${withCountryCode}50${phoneDigits}"
        return withCountryCode + "930108339"
    }

    fun UUID(): String {
        val uuidDigits = Random().nextInt(900000000) + 100000000
        return "550e8400-e29b-41d4-a716-123${uuidDigits}"
    }

    fun deviceId(): String {
        val deviceID = Random().nextInt(900000000) + 100000000
        return "0d69931d-1026-44dd-9e2f-0a4${deviceID}"
    }
}