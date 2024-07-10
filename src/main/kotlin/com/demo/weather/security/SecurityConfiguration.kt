package com.demo.weather.security

import com.demo.weather.configuration.properties.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
class SecurityConfiguration {

    @Bean
    fun springSecurityFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        http.csrf { s -> s.disable() }
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers("/api/v1/weather/**").hasAnyAuthority("USER", "CLIENT")
                    .anyExchange().authenticated()
            }
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun userDetailsService(securityProperties: SecurityProperties): MapReactiveUserDetailsService {
        val userDetails = securityProperties.users
            .map { user ->
                User.withUsername(user.username)
                    .password(passwordEncoder().encode(user.password))
                    .authorities(*user.authorities)
                    .disabled(user.disabled)
                    .build()
            }
            .toList()
        return MapReactiveUserDetailsService(userDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}