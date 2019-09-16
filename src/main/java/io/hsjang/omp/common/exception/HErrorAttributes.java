package io.hsjang.omp.common.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

//@Component  ?
public class HErrorAttributes implements ErrorAttributes {

    private static final String ERROR_ATTRIBUTE = HErrorAttributes.class.getName() + ".ERROR";

    @Override
    public Throwable getError(ServerRequest request) {
        return (Throwable) request.attribute(ERROR_ATTRIBUTE) .orElseThrow(() -> new IllegalStateException( "Missing exception attribute in ServerWebExchange"));
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>(); 
        errorAttributes.put("timestamp", new Date()); 
        errorAttributes.put("path", request.path()); 
        errorAttributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); 
        return errorAttributes;
    }

    @Override
    public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
        exchange.getAttributes().putIfAbsent(ERROR_ATTRIBUTE, error);

    }

}