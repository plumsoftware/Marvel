package ru.plumsoftware.data.constant

import ru.plumsoftware.data.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

class ApiConstants {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"

        val ts = "1"
        const val PUBLIC_API_KEY: String = BuildConfig.PUBLIC_API_KEY
        private const val PRIVATE_API_KEY: String = BuildConfig.PRIVATE_API_KEY
        const val LIMIT = "20"

        fun hash(): String {
            val strToMD = "$ts$PRIVATE_API_KEY$PUBLIC_API_KEY"
            val md5 = MessageDigest.getInstance("MD5")

            return BigInteger(1, md5.digest(strToMD.toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }
    }
}