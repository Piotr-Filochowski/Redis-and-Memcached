package com.filochowski.pm

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfig(
    @param:Value("\${redis.hostname}") private val HOSTNAME: String,
    @param:Value("\${redis.port}") private val PORT: Int,
    @param:Value("\${redis.database}") private val DATABASE: Int,
    @param:Value("\${redis.password}") private val PASSWORD: String,
    @param:Value("\${redis.timeout}") private val TIMEOUT: Long
) {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration()
        config.hostName = HOSTNAME
        config.port = PORT
        config.database = DATABASE
        config.setPassword(PASSWORD)
        val clientConfig = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(TIMEOUT))
            .build()
        return LettuceConnectionFactory(config, clientConfig)
    }

    @Bean
    fun stringRedisTemplate(
        @Qualifier("redisConnectionFactory") redisConnectionFactory: RedisConnectionFactory
    ): StringRedisTemplate {
        val template = StringRedisTemplate()
        template.setConnectionFactory(redisConnectionFactory)
        template.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = GenericJackson2JsonRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }

//    @Bean
//    fun messagePackRedisTemplate(
//        @Qualifier("redisConnectionFactory") redisConnectionFactory: RedisConnectionFactory
//    ): RedisTemplate<String, ByteArray> {
//        val template = RedisTemplate<String, ByteArray>()
//        template.setConnectionFactory(redisConnectionFactory)
//        template.keySerializer = StringRedisSerializer()
//        template.isEnableDefaultSerializer = false
//        return template
//    }

    @Bean
    fun messagePackObjectMapper(): ObjectMapper {
        return ObjectMapper(JsonFactory())
            .registerModule(JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}