package com.jjunpro.reactive.web;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.jjunpro.reactive.web.handler.HotelHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Routing {

    @Bean
    public RouterFunction<ServerResponse> routingFunction(HotelHandlers hotelHandlers) {
        return RouterFunctions
            .nest(
                path("/hotel"),
                RouterFunctions
                    .route(GET("").and(accept(MediaType.APPLICATION_JSON)), hotelHandlers::findAllHotels)
            )
            .andNest(
                path("/hotel"),
                RouterFunctions
                    .route(POST("").and(accept(MediaType.APPLICATION_JSON)), hotelHandlers::createHotel)
            )
            ;
    }
}