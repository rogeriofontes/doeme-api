package br.com.doeme;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CaffeineCache pessoasCache() {
        return new CaffeineCache("pessoasCache",
                Caffeine.newBuilder()
                        .expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
    }

    @Bean
    public CaffeineCache fornecedorCache() {
        return new CaffeineCache("fornecedorCache",
                Caffeine.newBuilder()
                        .expireAfterWrite(7, TimeUnit.DAYS).maximumSize(100).build());
    }
}
