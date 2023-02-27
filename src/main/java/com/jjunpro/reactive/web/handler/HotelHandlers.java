package com.jjunpro.reactive.web.handler;

import com.jjunpro.reactive.application.service.HotelService;
import com.jjunpro.reactive.domain.hotel.dto.CreateHotelDto;
import com.jjunpro.reactive.domain.hotel.dto.GetHotelUserDto;
import com.jjunpro.reactive.web.config.GlobalRoutingHandler;
import com.jjunpro.reactive.web.error.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HotelHandlers {

    private final HotelService    hotelService;
    private final ObjectValidator validator;

    public Mono<ServerResponse> findAllHotels(ServerRequest serverRequest) {
        String token = serverRequest.headers().firstHeader("Authorization");
        return ServerResponse
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(hotelService.findAllHotels(token), GetHotelUserDto.class);
    }

    public Mono<ServerResponse> createHotel(ServerRequest serverRequest) {
        String token          = serverRequest.headers().firstHeader("Authorization");
        var createUserDtoMono = serverRequest.bodyToMono(CreateHotelDto.class).doOnNext(validator::validate);
        return GlobalRoutingHandler.doRequest(hotelService.addHotel(createUserDtoMono, token), HttpStatus.CREATED);
    }
}
