package com.jjunpro.reactive.domain.hotel.dto;

import com.jjunpro.reactive.domain.hotel.type.Amenities;
import java.time.LocalDateTime;
import java.util.List;

public record GetHotelUserDto(
    String id,
    String name,
    String phoneNumber,
    String location,
    List<Amenities> amenities,
    String userId,
    LocalDateTime createdDate,
    GetUserDto hotelUserDto
) {

}