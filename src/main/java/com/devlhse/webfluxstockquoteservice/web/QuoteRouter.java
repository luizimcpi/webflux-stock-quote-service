package com.devlhse.webfluxstockquoteservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(QuoteHandler handler){
        return RouterFunctions
                .route(GET("/quotes").and(accept(MediaType.APPLICATION_JSON)), handler::fetchQuotes)
                .andRoute(GET("/quotes/stream").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::streamQuotes);
    }

    private static void auth(String scope) throws Exception {
        if(scope == "GET_USER"){
            throw new Exception("Você não tem permissão suficiente para executar esta ação");
        }
    }



}
