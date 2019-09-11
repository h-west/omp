package io.hsjang.omp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hsjang.omp.common.HMap;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/ms")
    public Flux<HMap> test(){
        return Flux.just(HMap.of("1","2"));
    }
}