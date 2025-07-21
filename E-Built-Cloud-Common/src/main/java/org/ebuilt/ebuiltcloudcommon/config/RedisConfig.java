package org.ebuilt.ebuiltcloudcommon.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@Slf4j
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate( RedisConnectionFactory redisConnectionFactory) {
        log.info("=======开始创建redis模板对象=======");
        RedisTemplate redisTemplate = new RedisTemplate<>();
        /*设置redis链接工厂*/
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        /*设置redis key 序列化器*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
