package io.hsjang.omp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import io.hsjang.omp.common.document.Doc;
import io.hsjang.omp.repository.DocCrudRepository;
import io.hsjang.omp.repository.DocReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

    @Autowired
    DocCrudRepository crudRepo;

    @Autowired
    DocReactiveRepository reactiveRepo;

    @Autowired
    ReactiveMongoTemplate template;

	@Test
    public void crudRepoTest() {
        crudRepo.save(new Doc("id","name")).block();
        Flux<Doc> crudRepoFlux = crudRepo.findAll();
    
        StepVerifier
        .create(crudRepoFlux)
        .assertNext(doc -> {
            assertEquals("name" , doc.getName());
            assertNotNull(doc.getId());
        })
        .expectComplete()
        .verify();
    }
    
    @Test
    public void reactiveRepoTest() {
        reactiveRepo.save(new Doc("id","name")).block();
        Flux<Doc> reactiveRepoFlux = reactiveRepo.findAll();
    
        StepVerifier
        .create(reactiveRepoFlux)
        .assertNext(doc -> {
            assertEquals("name" , doc.getName());
            assertNotNull(doc.getId());
        })
        .expectComplete()
        .verify();
    }
    
    @Test
    public void templateTest() {
        template.save(new Doc("id","name")).block();
        Flux<Doc> templateFlux = template.findAll(Doc.class);
    
        StepVerifier
        .create(templateFlux)
        .assertNext(doc -> {
            assertEquals("name" , doc.getName());
            assertNotNull(doc.getId());
        })
        .expectComplete()
        .verify();
    }

}
