import com.filochowski.pm.UserDto
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
open class RedisConfig {

    @Bean
    fun redisTemplate(): RedisTemplate<String, UserDto>? {
        val template = RedisTemplate<String, UserDto>()
//        template.setConnectionFactory(connectionFactory)
        template.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = GenericJackson2JsonRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }
}