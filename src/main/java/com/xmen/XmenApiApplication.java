package com.xmen;

import com.xmen.application.usecases.MutantUseCase;
import com.xmen.domain.repositories.AttemptRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.xmen.infrastructure"
})
@EnableJpaRepositories(basePackages = {
        "com.xmen.infrastructure.repositories"
})
@EntityScan(basePackages = {
        "com.xmen.infrastructure.repositories.models"
})
public class XmenApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmenApiApplication.class, args);
    }

    @Bean
    public MutantUseCase mutantUseCase(final AttemptRepository repository){
        return new MutantUseCase(repository);
    }
}
