package com.demo.weather.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("security")
data class SecurityProperties(val users: List<User>) {
}

data class User(
    val username: String,
    val password: String,
    val authorities: Array<String>,
    val disabled : Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (password != other.password) return false
        if (!authorities.contentEquals(other.authorities)) return false
        if (disabled != other.disabled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + authorities.contentHashCode()
        result = 31 * result + disabled.hashCode()
        return result
    }
}