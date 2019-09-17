package io.hsjang.omp.common.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    MongoClient mongoClient;

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(); 
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }
    
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, "test");
    }
}