package io.hsjang.omp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import io.hsjang.omp.common.document.Doc;
import reactor.core.publisher.Flux;

@Repository
public interface DocCrudRepository extends ReactiveMongoRepository<Doc,String>{
    Flux<Doc> findByName(String name);
}