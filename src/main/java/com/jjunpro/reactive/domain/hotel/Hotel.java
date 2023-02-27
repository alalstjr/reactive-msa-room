package com.jjunpro.reactive.domain.hotel;

import com.jjunpro.reactive.domain.hotel.dto.GetHotelDto;
import com.jjunpro.reactive.domain.hotel.dto.GetHotelUserDto;
import com.jjunpro.reactive.domain.hotel.dto.GetUserDto;
import com.jjunpro.reactive.domain.hotel.type.Amenities;
import com.jjunpro.reactive.domain.room.Room;
import com.jjunpro.reactive.infrastructure.persistence.entity.HotelEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Hotel {

    private String          id;
    private String          name;
    private String          phoneNumber;
    private String          location;
    private List<Room>      rooms;
    private List<Amenities> amenities;
    private String          userId;
    private LocalDateTime   createdDate;
    private LocalDateTime   modifiedDate;

    public HotelEntity toEntity() {
        return HotelEntity
            .builder()
            .id(id)
            .name(name)
            .phoneNumber(phoneNumber)
            .location(location)
            .rooms(rooms)
            .amenities(amenities)
            .userId(userId)
            .createdDate(createdDate)
            .modifiedDate(modifiedDate)
            .build();
    }

    public GetHotelDto toGetHotelDto() {
        return new GetHotelDto(id, name, phoneNumber, location, amenities, userId, createdDate);
    }

    public GetHotelUserDto toGetHotelUserDto(GetUserDto hotelUserDto) {
        return new GetHotelUserDto(id, name, phoneNumber, location, amenities, userId, createdDate, hotelUserDto);
    }
}
