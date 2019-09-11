package io.hsjang.omp;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebExceptionHandler;

import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableWebFlux
public class Application implements WebFluxConfigurer{

	@Value("classpath:/static/index.html") Resource index;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RouterFunction<ServerResponse> resources(@Value("classpath:/static/index.html") Resource index) {
		return RouterFunctions.route(RequestPredicates.GET("/"), req -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(index));
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/", "classpath:/static/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

	@Bean
	@Order(-1)
	public WebExceptionHandler exHandler(){
		return (exchange, throwable) -> {
			System.out.println("ERRRR!!");  // throwable 의 종류에 따라서 status 를 가져와서 처리면 될것 같음.
			return Mono.empty();
			// if (exchange.getResponse().isCommitted() || isDisconnectedClientError(throwable)) {
			// 	return Mono.error(throwable);
			// }
			// this.errorAttributes.storeErrorInformation(throwable, exchange);
			// ServerRequest request = ServerRequest.create(exchange, this.messageReaders);
			// return getRoutingFunction(this.errorAttributes).route(request).switchIfEmpty(Mono.error(throwable))
			// 		.flatMap((handler) -> handler.handle(request))
			// 		.doOnNext((response) -> logError(request, response, throwable))
			// 		.flatMap((response) -> write(exchange, response));
		};
	}
}
