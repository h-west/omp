package io.hsjang.omp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hsjang.omp.common.HMap;
import io.hsjang.omp.common.ResponseCode;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/ms")
    public Flux<HMap> test(){
        return Flux.just(HMap.of("1","2"));
    }

    @GetMapping("/mss")
    public Flux<HMap> tests(){
        ResponseCode.PARAM_CHECK.exParams("[paramName]");
        return Flux.just(HMap.of("1","2"));
    }
}