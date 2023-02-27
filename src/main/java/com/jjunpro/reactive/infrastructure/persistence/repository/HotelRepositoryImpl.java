package com.jjunpro.reactive.infrastructure.persistence.repository;

import com.jjunpro.reactive.domain.hotel.Hotel;
import com.jjunpro.reactive.domain.hotel.repository.HotelRepository;
import com.jjunpro.reactive.infrastructure.persistence.dao.HotelDao;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 회원 정보를 DB 에 직접 연결해서 가져오는 저장소
 * @author jjunpro
 * @since 2023/02/26 PM 15:33
 */
@Repository
@RequiredArgsConstructor
public class HotelRepositoryImpl implements HotelRepository {

    private final HotelDao hotelDao;

    @Override
    public Flux<Hotel> findAll() {
        return hotelDao
            .findAll()
            .flatMap(hotel -> Mono.just(hotel.toHotel()));
    }

    @Override
    public Flux<Hotel> findAllById(List<String> ids) {
        return null;
    }

    @Override
    public Mono<Hotel> findById(String id) {
        return null;
    }

    @Override
    public Mono<Hotel> save(Hotel hotel) {
        return hotelDao
            .save(hotel.toEntity())
            .flatMap(
                hotelEntity -> Mono.just(hotelEntity.toHotel())
            );
    }

    @Override
    public Mono<Hotel> delete(String id) {
        return null;
    }
}