package com.jjunpro.reactive.infrastructure.persistence.dao;

import com.jjunpro.reactive.infrastructure.persistence.entity.HotelEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HotelDao extends ReactiveMongoRepository<HotelEntity, String> {

}