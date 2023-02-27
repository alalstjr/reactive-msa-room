package com.jjunpro.reactive.domain.hotel.dto;

import com.jjunpro.reactive.domain.hotel.Hotel;
import com.jjunpro.reactive.domain.hotel.type.Amenities;
import com.jjunpro.reactive.domain.room.Room;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public record CreateHotelDto(

    @NotNull(message = "호텔 이름은 필수로 입력해야합니다.")
    @Size(max = 100, message = "최대 100글자까지만 입력 가능합니다.")
    String name,

    @NotNull(message = "호텔 전화번호는 필수로 입력해야합니다.")
    String phoneNumber,

    @NotNull(message = "호텔 위치는 필수로 입력해야합니다.")
    String location,

    List<Room> rooms,

    List<Amenities> amenities
) {
    public Hotel toHotel(String userId) {
        return Hotel
            .builder()
            .name(name)
            .phoneNumber(phoneNumber)
            .location(location)
            .amenities(amenities)
            .userId(userId)
            .createdDate(LocalDateTime.now())
            .build();
    }
}
