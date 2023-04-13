import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableRedisRepositories
class RedisConfig {
    @Bean
    fun connectionFactory(): JedisConnectionFactory {
        val configuration = RedisStandaloneConfiguration()
        configuration.setHostName("localhost")
        configuration.setPort(6379)
        return JedisConnectionFactory(configuration)
    }

    @Bean
    fun template(): RedisTemplate<String, Any> {
        val template: RedisTemplate<String, Any> = RedisTemplate()
        template.setConnectionFactory(connectionFactory())
        template.setKeySerializer(StringRedisSerializer())
        template.setHashKeySerializer(StringRedisSerializer())
        template.setHashKeySerializer(JdkSerializationRedisSerializer())
        template.setValueSerializer(JdkSerializationRedisSerializer())
        template.setEnableTransactionSupport(true)
        template.afterPropertiesSet()
        return template
    }
}