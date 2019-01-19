package org.shahin.nazarov.caching.config;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class MemcachedConfig {

    @Bean
    public CacheFactory defaultCache(){
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName("testCache");
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cacheFactory.setAddressProvider(new DefaultAddressProvider("127.0.0.1:11211"));
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setConsistentHashing(true);
        cacheFactory.setConfiguration(cacheConfiguration);
        return cacheFactory;
    }


    @Bean
    public SSMCacheManager cacheManager(@Autowired Cache defaultCache){
        SSMCacheManager cacheManager = new SSMCacheManager();
        SSMCache cache = new SSMCache(defaultCache, 300, false);
        cacheManager.setCaches(Arrays.asList(cache));
        return cacheManager;
    }

}
