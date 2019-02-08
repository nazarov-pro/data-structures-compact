package org.shahin.nazarov.utility.caching;

import org.shahin.nazarov.utility.caching.config.MemcachedConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@ImportAutoConfiguration(classes = MemcachedConfig.class)
public class Application {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class);
        Application application = new Application();
        System.out.println(application.getStrings());
        System.out.println(application.getStrings());
        System.out.println(application.getStrings());
    }

    @Cacheable(value = "strings", key = "'main'")
    public List<String> getStrings() throws InterruptedException {
        System.out.println("Getting list");
        Thread.sleep(1000);
        return Arrays.asList("a1", "a2", "a3");
    }
}
