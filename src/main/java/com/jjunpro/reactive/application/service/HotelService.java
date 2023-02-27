package com.jjunpro.reactive.application.service;

import com.jjunpro.reactive.domain.hotel.Hotel;
import com.jjunpro.reactive.domain.hotel.dto.CreateHotelDto;
import com.jjunpro.reactive.domain.hotel.dto.GetHotelDto;
import com.jjunpro.reactive.domain.hotel.dto.GetHotelUserDto;
import com.jjunpro.reactive.domain.hotel.dto.GetUserDto;
import com.jjunpro.reactive.domain.hotel.repository.HotelRepository;
import com.jjunpro.reactive.infrastructure.messaging.KafkaProducer;
import com.jjunpro.reactive.web.security.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final KafkaProducer   producer;
    private final JWTUtil         jwtUtil;

    public Flux<GetHotelUserDto> findAllHotels(String token) {
        return hotelRepository
            .findAll()
            .flatMap(
                hotel -> {
                    producer.sendMessage("호텔을 조회합니다.");

                    return WebClient
                        .create("http://localhost:8080")
                        .get()
                        .uri(
                            uriBuilder -> uriBuilder
                                .path("/users/id/{userId}")
                                .build(hotel.getUserId())
                        )
                        .header("Authorization", token)
                        .retrieve()
                        .bodyToMono(GetUserDto.class)
                        .map(hotel::toGetHotelUserDto);
                }
            );
    }

    public Mono<GetHotelDto> addHotel(Mono<CreateHotelDto> createUserDtoMono, String token) {
        return createUserDtoMono.flatMap(
            createUserDto -> createHotel(createUserDto, token)
        );
    }

    /**
     * 새로운 호텔을 생성합니다.
     * @param createHotelDto
     * @return
     */
    private Mono<GetHotelDto> createHotel(CreateHotelDto createHotelDto, String token) {
        Claims allClaimsFromToken = jwtUtil.getAllClaimsFromToken(token);
        var hotel = createHotelDto.toHotel(allClaimsFromToken.get("_id").toString());
        return hotelRepository
            .save(hotel)
            .map(Hotel::toGetHotelDto);
    }
}
