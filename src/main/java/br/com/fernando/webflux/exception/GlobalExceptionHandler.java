package br.com.fernando.webflux.exception;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@Order(-2)
@Component
public class GlobalExceptionHandler  extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(
            ErrorAttributes errorAttributes,
            ResourceProperties resourceProperties,
            ApplicationContext applicationContext,
            ServerCodecConfigurer codecConfigurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(codecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(),this::formattErrorResponse);
    }

    private Mono<ServerResponse> formattErrorResponse(ServerRequest request ){
        String query = request.uri().getQuery();
        ErrorAttributeOptions errorAttributeOptions = isTraceEnable(query)
                ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE)
                : ErrorAttributeOptions.defaults();

        Map<String,Object> errorAtributesMap = getErrorAttributes(request, errorAttributeOptions);
        int status  =  (int) Optional.ofNullable(errorAtributesMap.get("status")).orElse(500);
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAtributesMap));
    }
    private boolean isTraceEnable(String query) {
        return !StringUtils.isEmpty(query) && query.contains("trace=true");
    }
}
